package sse.edu.SPR2024.service.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.CertificateRequestDTO;
import sse.edu.SPR2024.dto.request.LearningInformationCourseRequestDTO;
import sse.edu.SPR2024.dto.request.LearningInformationRequestDTO;
import sse.edu.SPR2024.dto.response.LearningInformationResponseDTO;
import sse.edu.SPR2024.entity.*;
import sse.edu.SPR2024.enums.LearningInfoStatus;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.*;
import sse.edu.SPR2024.service.ILearningInformationService;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static sse.edu.SPR2024.consts.Constant.EMAIL_CONTENT;
import static sse.edu.SPR2024.consts.Constant.EMAIL_SUBJECT;

@Service
@RequiredArgsConstructor
public class LearningInformationService implements ILearningInformationService {
    private final ILearnerRepository learnerRepository;
    private final ICertificateRepository certificateRepository;
    private final IEnrollmentRepository enrollmentRepository;
    private final ICourseRepository courseRepository;
    private final EmailService emailService;
    private final IUserRepository userRepository;
    private final ISubscriptionRepository subscriptionRepository;
    private final IGroupRepository groupRepository;
    private final ILearningInformationRepository learningInformationRepository;
    private final ModelMapper modelMapper;


    @Override
    public LearningInformationResponseDTO create(LearningInformationRequestDTO learningInformationRequestDTO) {
        Learner learner = learnerRepository
                .findById(learningInformationRequestDTO.getLearnerId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Learner not found with id: {0}", learningInformationRequestDTO.getLearnerId())));

        Group group = groupRepository
                .findById(learningInformationRequestDTO.getGroupId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Group not found with id: {0}", learningInformationRequestDTO.getGroupId())));

        LearningInformation learningInformation = modelMapper.map(learningInformationRequestDTO, LearningInformation.class);

        learningInformation.setLearner(learner);
        learningInformation.setGroup(group);
        learningInformation.setCourseId(group.getSubscription().getCoursePackage().getCourse().getId());
        learningInformation.setStatus(LearningInfoStatus.INPROGRESS.name());
        learningInformation.setAssignDate(OffsetDateTime.now());

        return modelMapper.map(learningInformationRepository.save(learningInformation), LearningInformationResponseDTO.class);
    }

    @Override
    public LearningInformationResponseDTO update(LearningInformationRequestDTO learningInformationRequestDTO) {
        LearningInformation learningInformation = learningInformationRepository
                .findById(learningInformationRequestDTO.getId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Learning Information not found with id: {0}", learningInformationRequestDTO.getId())));

        learningInformation.setLearnedLesson(learningInformationRequestDTO.getLearnedLesson());
        learningInformation.setEnrollDate(learningInformationRequestDTO.getEnrollDate());
        learningInformation.setStatus(learningInformationRequestDTO.getStatus());

        return modelMapper.map(learningInformationRepository.save(learningInformation), LearningInformationResponseDTO.class);
    }

    @Override
    public LearningInformationResponseDTO getLearningInformationById(String id) {
        return learningInformationRepository
                .findById(id)
                .map(learningInformation -> modelMapper.map(learningInformation, LearningInformationResponseDTO.class))
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Learning Information not found with id: {0}", id)));
    }

    @Override
    public LearningInformationResponseDTO getLearningInfoByUser(LearningInformationCourseRequestDTO learningInformationCourseRequestDTO) {
        Learner learner = learnerRepository
                .findById(learningInformationCourseRequestDTO.getLearnerId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Learner not found with id: {0}", learningInformationCourseRequestDTO.getLearnerId())));

        if (learner.getOrganization() == null) {
            throw new ServiceDataException(MessageFormat.format("Learner not found organization with id: {0}", learningInformationCourseRequestDTO.getLearnerId()));
        }

        AtomicReference<LearningInformationResponseDTO> learningInformationResponseDTO = new AtomicReference<>();

        List<Subscription> subscriptions = subscriptionRepository.findSubscriptionsByOrgOrgIdAndCoursePackage_Course_Id(learner.getOrganization().getOrgId(), learningInformationCourseRequestDTO.getCourseId());

        subscriptions.forEach(subscription -> {
            Set<Group> groups = subscription.getSubscriptionGroups();

            groups.forEach(group -> {
               Set<LearningInformation> learningInfomation = group.getLearningInformations();

               learningInfomation.forEach(learningInformation -> {
                   if (learningInformation.getLearner().getId().equals(learner.getId())) {
                       learningInformationResponseDTO.set(new LearningInformationResponseDTO());
                       learningInformationResponseDTO.set(modelMapper.map(learningInformation, LearningInformationResponseDTO.class));
                   }
               });
            });
        });

        if (Objects.isNull(learningInformationResponseDTO.get())) {
            throw new ServiceDataException(MessageFormat.format("Learning Information not found with learnerId: {0} and courseId: {1}", learningInformationCourseRequestDTO.getLearnerId(), learningInformationCourseRequestDTO.getCourseId()));
        }

        return learningInformationResponseDTO.get();
    }

    @Override
    public LearningInformationResponseDTO getLearningInformationByLearnerAndGroup(LearningInformationRequestDTO learningInformationRequestDTO) {
        LearningInformation learningInformation = learningInformationRepository
                .findFirstByLearner_IdAndGroup_Id(learningInformationRequestDTO.getLearnerId(), learningInformationRequestDTO.getGroupId());

        if (Objects.isNull(learningInformation)) {
            throw new ServiceDataException(MessageFormat.format("Learning Information not found with learnerId: {0} and groupId: {1}", learningInformationRequestDTO.getLearnerId(), learningInformationRequestDTO.getGroupId()));

        }

        return modelMapper.map(learningInformation, LearningInformationResponseDTO.class);
    }

    @Override
    public LearningInformationResponseDTO getLearningInformationByGroupId(String groupId) {
        LearningInformation learningInformation = learningInformationRepository
                .findLearningInformationByGroup_Id(groupId);

        if (Objects.isNull(learningInformation)) {
            throw new ServiceDataException(MessageFormat.format("Learning Information not found with groupId: {0}", groupId));
        }

        return modelMapper.map(learningInformation, LearningInformationResponseDTO.class);
    }
    @Override
    public LearningInformationResponseDTO getLearningInforByLearner(String id) {
        LearningInformation learningInformation = learningInformationRepository.findFirstByLearnerIdOrderByAssignDateDesc(id);
        if (Objects.isNull(learningInformation)) {
            throw new ServiceDataException(MessageFormat.format("Learning Information not found with learnerId: {0} ",id));

        }
        return modelMapper.map(learningInformation, LearningInformationResponseDTO.class);
    }

    @Override
    public LearningInformationResponseDTO updateLearnedLesson(LearningInformationRequestDTO learningInformationRequestDTO) {
        LearningInformation learningInformation = learningInformationRepository.findFirstByLearnerIdAndCourseId(learningInformationRequestDTO.getLearnerId(), learningInformationRequestDTO.getCourseId());
        if (Objects.isNull(learningInformation)) {
            throw new ServiceDataException(MessageFormat.format("Learning Information not found with learnerId: {0} ",learningInformationRequestDTO.getLearnerId()));
        }

        if(learningInformation.getLearnedLesson().isEmpty()){
            learningInformation.setLearnedLesson(learningInformationRequestDTO.getLearnedLesson().trim());
        }
        else {
            learningInformation.setLearnedLesson(learningInformation.getLearnedLesson() + "," + learningInformationRequestDTO.getLearnedLesson().trim());

        }

        learningInformation.setEnrollDate(learningInformation.getEnrollDate());
        learningInformation.setStatus(learningInformation.getStatus());

        LearningInformation learningInformationResult = learningInformationRepository.save(learningInformation);

        Course course = courseRepository.findById(learningInformation.getCourseId()).orElseThrow(() -> new ServiceDataException(MessageFormat.format("Course not found with id: {0}", learningInformation.getCourseId())));

        List<String> lessonsInCourse = course.getCourseModules()
                .stream()
                .flatMap(module -> module.getModuleLessons().stream())
                .map(Lesson::getId)
                .collect(Collectors.toList());

        List<String> learnedLessons = List.of(learningInformation.getLearnedLesson().split(","));

        if (lessonsInCourse.containsAll(learnedLessons)) {
            learningInformation.setStatus(LearningInfoStatus.COMPLETED.name());
            learningInformationResult = learningInformationRepository.save(learningInformation);

            Enrollment enrollment = enrollmentRepository.findFirstByCourseIdAndLearnerId(learningInformation.getCourseId(), learningInformation.getLearner().getId());

            if (Objects.isNull(enrollment)) {
                throw new ServiceDataException(MessageFormat.format("Enrollment not found with courseId: {0} and learnerId: {1}", learningInformation.getCourseId(), learningInformation.getLearner().getId()));
            }

            Certificate certificate = Certificate
                    .builder()
                    .certificateUrl("enrollment=" + enrollment.getId())
                    .receiveDate(LocalDateTime.now())
                    .description("")
                    .imgUrl("")
                    .certificateEnrollments(Set.of())
                    .name("")
                    .build();

            Certificate certificateResult = certificateRepository.save(certificate);

            if (Objects.isNull(certificateResult)) {
                throw new ServiceDataException("Certificate not created");
            }

            User user = userRepository
                    .findById(learningInformation.getLearner().getId())
                    .orElseThrow(() -> new ServiceDataException(MessageFormat.format("User not found with id: {0}", learningInformation.getLearner().getId())));

            if (Objects.isNull(user)) {
                throw new ServiceDataException(MessageFormat.format("User not found with id: {0}", learningInformation.getLearner().getId()));
            }

            try {
                emailService.sendHtmlEmail(user.getEmail(), EMAIL_SUBJECT, String.format(EMAIL_CONTENT, user.getFullName(), course.getName(), "http://localhost:3000/certificate?" + certificateResult.getCertificateUrl()));
            } catch (MessagingException e) {
                throw new ServiceDataException("Email not sent");
            }

        }

        return modelMapper.map(learningInformationResult, LearningInformationResponseDTO.class);
    }
}
