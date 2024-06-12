package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.EnrollmentRequestDTO;
import sse.edu.SPR2024.dto.response.EnrollmentResponseDTO;
import sse.edu.SPR2024.dto.response.UserBasicResponseDTO;
import sse.edu.SPR2024.entity.*;
import sse.edu.SPR2024.enums.EnrollmentStatus;
import sse.edu.SPR2024.enums.LearningInfoStatus;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.*;
import sse.edu.SPR2024.service.IEnrollmentService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class EnrollmentService implements IEnrollmentService {
    private final ILearnerRepository learnerRepository;
    private final IEnrollmentRepository enrollmentRepository;
    private final ICourseRepository courseRepository;
    private final IUserRepository userRepository;
    private final IOrganizationRepository organizationRepository;
    private final ILearningInformationRepository learningInformationRepository;
    private final ISubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;
    @Override
    public EnrollmentResponseDTO enroll(EnrollmentRequestDTO enrollmentRequestDTO) {
        Learner learner = learnerRepository
                .findById(enrollmentRequestDTO.getLearnerId())
                .orElseThrow(() -> new ServiceDataException("Learner not found"));

        if (learner.getOrganization() == null) {
            throw new ServiceDataException("Learner has no organization");
        }

        Course course = courseRepository
                .findById(enrollmentRequestDTO.getCourseId())
                .orElseThrow(() -> new ServiceDataException("Course not found"));

        List<Subscription> subscription = subscriptionRepository
                .findSubscriptionsByOrgOrgIdAndCoursePackage_Course_Id(learner.getOrganization().getOrgId(), course.getId());

        AtomicBoolean isValid = new AtomicBoolean(false);
        subscription.stream().findAny().ifPresent(sub -> {
            if (sub.getEndDate().isAfter(OffsetDateTime.now())) {
                isValid.set(true);
            }
        });

        if (!isValid.get()) {
            throw new ServiceDataException("Subscription not found or expired");
        }

        Enrollment enrollment = modelMapper.map(enrollmentRequestDTO, Enrollment.class);
        enrollment.setLearner(learner);
        enrollment.setCourse(course);
        enrollment.setStatus(EnrollmentStatus.ENROLLED.name());

        LearningInformation learningInformation = learningInformationRepository.findFirstByLearnerIdAndCourseId(learner.getId(), course.getId());
        if (learningInformation == null) {
           learningInformation.setStatus(LearningInfoStatus.INPROGRESS.name());
            learningInformationRepository.save(learningInformation);
        }

        return modelMapper.map(enrollmentRepository.save(enrollment), EnrollmentResponseDTO.class);
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByLearnerId(String learnerId) {
        List<Enrollment> enrollments = enrollmentRepository.getEnrollmentsByLearner_Id(learnerId);

        return enrollments.stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentResponseDTO.class))
                .toList();
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByCourseId(String courseId) {
        List<Enrollment> enrollments = enrollmentRepository.getEnrollmentsByCourse_Id(courseId);

        return enrollments.stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentResponseDTO.class))
                .toList();
    }

    @Override
    public EnrollmentResponseDTO updateEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) {
        Enrollment enrollment = enrollmentRepository
                .findById(enrollmentRequestDTO.getId())
                .orElseThrow(() -> new ServiceDataException("Enrollment not found"));

        enrollment.setStatus(enrollmentRequestDTO.getStatus());

        return modelMapper.map(enrollmentRepository.save(enrollment), EnrollmentResponseDTO.class);
    }

    @Override
    public EnrollmentResponseDTO getById(String id) {
        Enrollment enrollment = enrollmentRepository
                .findById(id)
                .orElseThrow(() -> new ServiceDataException("Enrollment not found"));

        User user = userRepository.findById(enrollment.getLearner().getId()).orElseThrow(() -> new ServiceDataException("User not found"));

        EnrollmentResponseDTO enrollmentResponseDTO = modelMapper.map(enrollment, EnrollmentResponseDTO.class);
        UserBasicResponseDTO userBasicResponseDTO = new UserBasicResponseDTO();
        userBasicResponseDTO.setFullName(user.getFullName());

        enrollmentResponseDTO.setUser(userBasicResponseDTO);

        return enrollmentResponseDTO;
    }
}
