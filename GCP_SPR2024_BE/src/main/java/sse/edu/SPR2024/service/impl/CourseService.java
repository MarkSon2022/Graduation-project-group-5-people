package sse.edu.SPR2024.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sse.edu.SPR2024.dto.request.CourseModuleRequestDTO;
import sse.edu.SPR2024.dto.request.CoursePackageRequestDTO;
import sse.edu.SPR2024.dto.request.CourseRequestDTO;
import sse.edu.SPR2024.dto.request.SearchCourseRequestDTO;
import sse.edu.SPR2024.dto.response.CourseModuleResponseDTO;
import sse.edu.SPR2024.dto.response.CoursePackageResponseDTO;
import sse.edu.SPR2024.dto.response.CourseResponseDTO;
import sse.edu.SPR2024.entity.*;
import sse.edu.SPR2024.enums.CourseStatus;
import sse.edu.SPR2024.enums.ProjectStatus;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.ICourseModuleRepository;
import sse.edu.SPR2024.repository.ICourseRepository;
import sse.edu.SPR2024.repository.IProjectRepository;
import sse.edu.SPR2024.service.ICourseService;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static sse.edu.SPR2024.enums.CourseStatus.PENDING;

@Service
@RequiredArgsConstructor
public class CourseService implements ICourseService {
    private final ICourseRepository courseRepository;
    private final ICourseModuleRepository courseModuleRepository;
    private final IProjectRepository projectRepository;
    private final CoursePackageService coursePackageService;
    private final CourseModuleService courseModuleService;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public CourseResponseDTO saveCourse(CourseRequestDTO course) {
        Optional<Project> project = projectRepository.findById(course.getProjectId());

        if (project.isEmpty()) {
            throw new ServiceDataException(MessageFormat.format("Can not find this project with id {0}", course.getProjectId()));
        }

        Course courseSave = modelMapper.map(course, Course.class);

        courseSave.setStatus(PENDING.name());
        courseSave.setProject(project.get());
        courseSave.setCourseCoursePackages(coursePackageService.getCoursePackageMappedWithCourse(courseSave, course.getCourseCoursePackages()));
        courseSave.setCourseModules(courseModuleService.getCourseModuleMappedWithCourse(courseSave, course.getCourseModules()));

        return modelMapper.map(courseRepository.save(courseSave), CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO updateCourse(CourseRequestDTO updatedCourse) {
        Course existingCourse = courseRepository.findById(updatedCourse.getId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Course not found with id: {0}", updatedCourse.getId())));

        existingCourse.setStatus(updatedCourse.getStatus());
        existingCourse.setName(updatedCourse.getName());
        existingCourse.setDuration(updatedCourse.getDuration());
        existingCourse.setImgUrl(updatedCourse.getImgUrl());
        existingCourse.setAge(updatedCourse.getAge());

        Project project = projectRepository.findById(updatedCourse.getProjectId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Project not found with id: {0}", updatedCourse.getProjectId())));

        existingCourse.setProject(project);
        existingCourse.setCourseCoursePackages(coursePackageService.getCoursePackageMappedWithCourse(existingCourse, updatedCourse.getCourseCoursePackages()));
        existingCourse.setCourseModules(courseModuleService.getCourseModuleMappedWithCourse(existingCourse, updatedCourse.getCourseModules()));

        return modelMapper.map(courseRepository.save(existingCourse), CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO approveCourse(CourseRequestDTO updatedCourse) {
        Course existingCourse = courseRepository.findById(updatedCourse.getId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Course not found with id: {0}", updatedCourse.getId())));

        existingCourse.setStatus(updatedCourse.getStatus());

        return modelMapper.map(courseRepository.save(existingCourse), CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO disableCourse(CourseRequestDTO course) {
        Course existingCourse = courseRepository.findById(course.getId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Course not found with id: {0}", course.getId())));

        existingCourse.setStatus(CourseStatus.INACTIVE.name());

        return modelMapper.map(courseRepository.save(existingCourse), CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO updateStatus(CourseRequestDTO course) {
        Course existingCourse = courseRepository.findById(course.getId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Course not found with id: {0}", course.getId())));
        existingCourse.setStatus(course.getStatus());

        return modelMapper.map(courseRepository.save(existingCourse), CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO getCourseById(String id) {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if (courseOptional.isEmpty()) {
            return null;
        }

        Course course = courseOptional.get();

        // Sort modules by createdDate in descending order
        List<CourseModule> sortedModules = course.getCourseModules().stream()
                .sorted(Comparator.comparing(CourseModule::getCreatedDate).reversed())
                .collect(Collectors.toList());

        // Sort lessons within each module by createdDate in descending order
        sortedModules.forEach(module -> {
            List<Lesson> sortedLessons = module.getModuleLessons().stream()
                    .sorted(Comparator.comparing(Lesson::getCreatedDate).reversed())
                    .collect(Collectors.toList());
            module.setModuleLessons(sortedLessons);
        });

        course.setCourseModules(sortedModules);

        return modelMapper.map(course, CourseResponseDTO.class);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, CourseResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getAllValidCourses() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        Root<Course> courseRoot = query.from(Course.class);
        Join<Course, Project> projectJoin = courseRoot.join("project", JoinType.INNER);

        Predicate courseStatusPredicate = criteriaBuilder.equal(courseRoot.get("status"), CourseStatus.ACTIVE.name());
        Predicate projectStatusPredicate = criteriaBuilder.equal(projectJoin.get("status"), ProjectStatus.ACTIVE.name());

        query.where(courseStatusPredicate, projectStatusPredicate);

        List<Course> courses = entityManager.createQuery(query).getResultList();

        if (Objects.isNull(courses) || CollectionUtils.isEmpty(courses)) {
            return List.of();
        }
        return courses
                .stream()
                .map(element ->
                    modelMapper.map(element, CourseResponseDTO.class)
                )
                .collect(Collectors.toList());

    }

    @Override
    public void deleteCourse(String id) {
//        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCourseMappedWithProject(Project project, List<CourseRequestDTO> courseRequestDTOs) {
        return courseRequestDTOs
                .stream()
                .map(element -> {
                    Course course = modelMapper.map(element, Course.class);
                    course.setProject(project);
                    return course;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO updateComment(CourseRequestDTO courseRequestDTO) {
        Course course = courseRepository.findById(courseRequestDTO.getId()).orElseThrow(() -> new ServiceDataException("Course not found"));

        course.setComment(courseRequestDTO.getComment());

        return modelMapper.map(courseRepository.save(course), CourseResponseDTO.class);
    }

}
