package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.response.DashboardResponseDTO;
import sse.edu.SPR2024.dto.response.ProjectDashboardResponseDTO;
import sse.edu.SPR2024.dto.response.SubscriptionResponseDTO;
import sse.edu.SPR2024.entity.Course;
import sse.edu.SPR2024.entity.Learner;
import sse.edu.SPR2024.entity.Organization;
import sse.edu.SPR2024.entity.Project;
import sse.edu.SPR2024.enums.CourseStatus;
import sse.edu.SPR2024.enums.ProjectStatus;
import sse.edu.SPR2024.enums.SubscriptionStatus;
import sse.edu.SPR2024.repository.*;
import sse.edu.SPR2024.service.IAdminService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final ICourseRepository courseRepository;
    private final ILearnerRepository learnerRepository;
    private final IProjectRepository projectRepository;
    private final IOrganizationRepository organizationRepository;
    private final ISubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;

    @Override
    public DashboardResponseDTO getDashboard() {
        List<Course> courseList = courseRepository.findAll();
        List<Learner> learnerList = learnerRepository.findAll();
        List<Project> projectList = projectRepository.findAll();
        List<Organization> organizationList = organizationRepository.findAll();

        Integer courseActiveNumber = courseList.stream().filter(course -> course.getStatus().equals(CourseStatus.ACTIVE.name())).toList().size();
        Integer courseInactiveNumber = courseList.stream().filter(course -> course.getStatus().equals(CourseStatus.INACTIVE.name())).toList().size();

        Integer learnerNumber = learnerList.stream().filter(learner -> learner.getUser().getStatus() == true).toList().size();
        Integer learnerInactiveNumber = learnerList.stream().filter(learner -> learner.getUser().getStatus() == false).toList().size();

        Integer projectNumber = projectList.stream().filter(project -> project.getStatus().equals(ProjectStatus.ACTIVE.name())).toList().size();
        Integer projectInactiveNumber = projectList.stream().filter(project -> project.getStatus().equals(ProjectStatus.INACTIVE.name())).toList().size();

        Integer organizationNumber = organizationList.stream().filter(organization -> organization.getUser().getStatus() == true).toList().size();
        Integer organizationInactiveNumber = organizationList.stream().filter(organization -> organization.getUser().getStatus() == false).toList().size();

        List<SubscriptionResponseDTO> subscriptionList = subscriptionRepository.findAll()
                .stream()
                .filter(subscription -> subscription.getStatus().equals(SubscriptionStatus.PAID.name()))
                        .map(subscription -> {
                            return modelMapper.map(subscription, SubscriptionResponseDTO.class);
                        })
                .collect(Collectors.toList());

        List<ProjectDashboardResponseDTO> projectDTOs = projectList.stream()
                .map(project -> {
                    ProjectDashboardResponseDTO dto = new ProjectDashboardResponseDTO();
                    dto.setId(project.getId());
                    dto.setAgeRecomment(project.getAgeRecomment());
                    dto.setGoal(project.getGoal());
                    dto.setIntroVideoUrl(project.getIntroVideoUrl());
                    dto.setSkill(project.getSkill());
                    dto.setStatus(project.getStatus());

                    // Calculate percentage
                    dto.setCourseNumber(String.valueOf(project.getProjectCourses().size()));
                    return dto;
                })
                .collect(Collectors.toList());

        return DashboardResponseDTO
                .builder()
                .courseNumber(courseActiveNumber)
                .inActiveCourseNumber(courseInactiveNumber)
                .learnerNumber(learnerNumber)
                .inActiveLearnerNumber(learnerInactiveNumber)
                .organizationNumber(organizationNumber)
                .inActiveOrganizationNumber(organizationInactiveNumber)
                .projectNumber(projectNumber)
                .inActiveProjectNumber(projectInactiveNumber)
                .subscription(subscriptionList)
                .project(projectDTOs)
                .build();

    }
}
