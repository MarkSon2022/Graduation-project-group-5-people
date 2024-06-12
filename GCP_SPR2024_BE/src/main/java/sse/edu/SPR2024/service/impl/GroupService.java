package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.CourseValidRequestDTO;
import sse.edu.SPR2024.dto.request.GroupRequestDTO;
import sse.edu.SPR2024.dto.response.GroupFullResponseDTO;
import sse.edu.SPR2024.dto.response.GroupResponseDTO;
import sse.edu.SPR2024.dto.response.LearningEnrollmentResponseDTO;
import sse.edu.SPR2024.dto.response.UserResponseDTO;
import sse.edu.SPR2024.entity.*;
import sse.edu.SPR2024.enums.EnrollmentStatus;
import sse.edu.SPR2024.enums.LearningInfoStatus;
import sse.edu.SPR2024.enums.UserRole;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.*;
import sse.edu.SPR2024.service.IGroupService;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static sse.edu.SPR2024.enums.GroupStatus.NEW;
import static sse.edu.SPR2024.utils.StringUtils.randomPassword;

@Service
@RequiredArgsConstructor
public class GroupService implements IGroupService {
    private final IGroupRepository groupRepository;
    private final IEnrollmentRepository enrollmentRepository;
    private final ICourseRepository courseRepository;
    private final ILearnerRepository learnerRepository;
    private final ISubscriptionRepository subscriptionRepository;
    private final ILearningInformationRepository learningInformationRepository;
    private final IMentorRepository mentorRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    /**
     * @param groupRequestDTO
     * @return
     */
    @Override
    public GroupResponseDTO create(GroupRequestDTO groupRequestDTO) {
        //TODO: Get UserId from authentication service and set Mentor for Group
        Group groupSave = modelMapper.map(groupRequestDTO, Group.class);
        groupSave.setCreatedDate(OffsetDateTime.now());
        groupSave.setStatus(NEW.name());
        return modelMapper.map(groupRepository.save(groupSave), GroupResponseDTO.class);
    }

    /**
     * @param groupRequestDTO
     * @return
     */
    @Override
    public GroupResponseDTO update(GroupRequestDTO groupRequestDTO) {
        Group existingGroup = groupRepository.findById(groupRequestDTO.getId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Group not found with id: {0}", groupRequestDTO.getId())));
        existingGroup.setName(groupRequestDTO.getName());
        existingGroup.setStatus(groupRequestDTO.getStatus());
//      TODO:  existingGroup.setLearningInformations(groupRequestDTO.getLearningInformations());
        existingGroup.setSubscription(subscriptionRepository
                .findById(groupRequestDTO.getSubscriptionId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Subscription not found with id: {0}",
                        groupRequestDTO.getId()))));
        existingGroup.setModifiedDate(OffsetDateTime.now());
        return modelMapper.map(groupRepository.save(existingGroup), GroupResponseDTO.class);
    }

    @Override
    public List<GroupResponseDTO> getListGroupByUserId(String userId) {
        List<Group> groups = groupRepository.findAll();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("User not found with id: {0}", userId)));

        if (user.getRole().equals(UserRole.MENTOR.name())) {
            return groups.stream()
                    .filter(group -> group.getMentor().getId().equals(userId))
                    .map(group -> {
                        GroupResponseDTO groupResponseDTO = modelMapper.map(group, GroupResponseDTO.class);
                        groupResponseDTO.setStudentExist(String.valueOf(learningInformationRepository.countLearningInformationsByGroupId(groupResponseDTO.getId())));
                        return groupResponseDTO;
                    })
                    .toList();
        }

        return groups.stream()
                .filter(x -> x.getSubscription().getEndDate().isAfter(OffsetDateTime.now()))
                .map(group -> modelMapper.map(group, GroupResponseDTO.class))
                .toList();
    }

    @Override
    public Boolean checkEnrollmentValid(CourseValidRequestDTO courseValidRequestDTO) {
        User user = userRepository.findById(courseValidRequestDTO.getLearnerId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("User not found with id: {0}", courseValidRequestDTO.getLearnerId())));

        LearningInformation learningInformationCheck = learningInformationRepository
                .findFirstByLearnerIdAndCourseIdAndStatus(courseValidRequestDTO.getLearnerId(), courseValidRequestDTO.getCourseId(), LearningInfoStatus.PENDING.name());

        if (learningInformationCheck == null) {
            return Boolean.FALSE;
        }
        else {
            LearningInformation learningInformation = learningInformationRepository.findFirstByLearnerIdAndCourseId(user.getLearner().getId(), courseValidRequestDTO.getCourseId());

            try {
                if (learningInformation != null) {
                    return learningInformation.getGroup().getSubscription().getEndDate().isAfter(OffsetDateTime.now());
                }
            }
            catch (Exception e) {
                return Boolean.FALSE;
            }
        }

        return learningInformationCheck.getStatus().equals(LearningInfoStatus.PENDING.name());
    }

    @Override
    public GroupResponseDTO importGroup(GroupRequestDTO groupRequestDTO) {
        Group group = modelMapper.map(groupRequestDTO, Group.class);

        Subscription subscription = subscriptionRepository
                .findById(groupRequestDTO.getSubscriptionId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Subscription not found with id: {0}",
                        groupRequestDTO.getSubscriptionId())));

        if (groupRequestDTO.getLearnerEmail().size() > subscription.getBoughtMaxStudent()) {
            throw new ServiceDataException(MessageFormat.format("Subscription has reached maximum student: {0}",
                    subscription.getBoughtMaxStudent()));
        }

        group.setSubscription(subscription);

        Mentor mentor = mentorRepository
                .findById(groupRequestDTO.getMentorId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Mentor not found with id: {0}",
                        groupRequestDTO.getMentorId())));
        group.setMentor(mentor);
        group.setCreatedDate(OffsetDateTime.now());
        group.setStatus(NEW.name());

        Organization organization = mentor.getOrganization();

        Group groupSaved = groupRepository.save(group);

        if (groupSaved != null) {
            List<User> users = new ArrayList<>();
            groupRequestDTO.getLearnerEmail().forEach(email -> {
                int learnerSize = learningInformationRepository.findLearningInformationByGroupSubscriptionId(subscription.getId()).size();

                if (learnerSize >= subscription.getBoughtMaxStudent()) {
                    throw new ServiceDataException(MessageFormat.format("Subscription has reached maximum student: {0}",
                            subscription.getBoughtMaxStudent()));
                }

                String id = UUID.randomUUID().toString();

                User userCheck = userRepository
                        .findFirstByEmail(email);

                if (userCheck != null) {
                    LearningInformation learningInformation = LearningInformation
                            .builder()
                            .group(group)
                            .assignDate(OffsetDateTime.now())
                            .learnedLesson("")
                            .courseId(subscription.getCoursePackage().getCourse().getId())
                            .status(LearningInfoStatus.PENDING.name())
                            .build();
                    learningInformation.setGroup(groupSaved);
                    learningInformation.setLearner(userCheck.getLearner());
                    learningInformationRepository.save(learningInformation);

                    userCheck.setOrganization(organization);

                   Optional<Learner> learnerCheck = learnerRepository.findById(userCheck.getUserId());

                   if (learnerCheck.isPresent()) {
                       learnerCheck.get().setOrganization(organization);
                       learnerRepository.save(learnerCheck.get());
                   }
                   else {
                       Learner learner = Learner
                               .builder()
                               .id(id)
                               .point(0L)
                               .ranking(0)
                               .build();

                    learner.setUser(userCheck);
                    learner.setOrganization(organization);
                    userCheck.setLearner(learner);
                    users.add(userCheck);
                   }
                }
                else {
                    Learner learner = Learner
                            .builder()
                            .id(id)
                            .point(0L)
                            .ranking(0)
                            .build();

                    User user = User
                            .builder()
                            .userId(id)
                            .status(true)
                            .email(email)
                            .fullName("")
                            .password(randomPassword(8))
                            .role(UserRole.LEARNER.name())
                            .learner(learner)
                            .build();
                    learner.setUser(user);
                    learner.setOrganization(organization);
                    user.setLearner(learner);
                    users.add(user);
                }

            });

            List<User> userListSaved = userRepository.saveAll(users);

            userListSaved.forEach(user -> {
                LearningInformation learningInformation = LearningInformation
                        .builder()
                        .group(group)
                        .assignDate(OffsetDateTime.now())
                        .learnedLesson("")
                        .courseId(subscription.getCoursePackage().getCourse().getId())
                        .status(LearningInfoStatus.PENDING.name())
                        .build();
                learningInformation.setGroup(groupSaved);
                learningInformation.setLearner(user.getLearner());
                learningInformationRepository.save(learningInformation);
            });
        }
        return modelMapper.map(groupSaved, GroupResponseDTO.class);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public GroupResponseDTO getGroupById(String id) {
        Optional<Group> groupOptional = groupRepository.findById(id);

        if (groupOptional.isEmpty()) {
            throw new ServiceDataException("Can not find group");
        }

        return modelMapper.map(groupOptional.get(), GroupResponseDTO.class);
    }

    @Override
    public GroupFullResponseDTO getGroupFullInfoById(String id) {
        Group groupOptional = groupRepository.findById(id).orElseThrow(() -> new ServiceDataException("Can not find group"));

        GroupFullResponseDTO groupResponseDTO = GroupFullResponseDTO
                .builder()
                .courseName(null)
                .id(groupOptional.getId())
                .name(groupOptional.getName())
                .status(groupOptional.getStatus())
                .build();

        List<LearningEnrollmentResponseDTO> learningEnrollmentResponseDTOS = new ArrayList<>();
        List<LearningInformation> learningInformations = learningInformationRepository.findAllByGroupId(groupOptional.getId());

        learningInformations.forEach(x -> {
           LearningEnrollmentResponseDTO learningEnrollmentResponseDTO = modelMapper.map(x, LearningEnrollmentResponseDTO.class);
           User user = userRepository.findById(x.getLearner().getId()).orElseThrow(() -> new ServiceDataException("Can not find user"));
           Enrollment enrollment = enrollmentRepository
                   .findFirstByCourseIdAndLearnerId(x.getCourseId(), user.getUserId());

           if (groupResponseDTO.getCourseName() == null) {
               Course course = courseRepository.findById(x.getCourseId()).orElseThrow(() -> new ServiceDataException("Can not find course"));
                groupResponseDTO.setCourseName(course.getName());
           }

            learningEnrollmentResponseDTO.setUserEmail(user.getEmail());
           if (Objects.isNull(enrollment)) {
               learningEnrollmentResponseDTO.setEnroll(Boolean.FALSE);
           }
           else {
               learningEnrollmentResponseDTO.setEnroll(enrollment.getStatus().equals(EnrollmentStatus.ENROLLED.name()));
           }

           learningEnrollmentResponseDTOS.add(learningEnrollmentResponseDTO);
       });

        groupResponseDTO.setLearningInformations(learningEnrollmentResponseDTOS);

        return groupResponseDTO;
    }

    /**
     * @param id
     */
    @Override
    public void deleteGroup(String id) {
        groupRepository.deleteById(id);
    }
}
