package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.CourseModuleRequestDTO;
import sse.edu.SPR2024.dto.request.CoursePackageRequestDTO;
import sse.edu.SPR2024.dto.response.CourseModuleResponseDTO;
import sse.edu.SPR2024.dto.response.CoursePackageResponseDTO;
import sse.edu.SPR2024.entity.Course;
import sse.edu.SPR2024.entity.CourseModule;
import sse.edu.SPR2024.entity.CoursePackage;
import sse.edu.SPR2024.repository.ICourseModuleRepository;
import sse.edu.SPR2024.repository.ICoursePackageRepository;
import sse.edu.SPR2024.service.ICourseModuleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseModuleService implements ICourseModuleService {
    private final LessonService lessonService;
    private final ModelMapper modelMapper;

    @Override
    public List<CourseModule> getCourseModuleMappedWithCourse(Course course, List<CourseModuleRequestDTO> courseModuleRequestDTOs) {
        return courseModuleRequestDTOs
                .stream()
                .map(element -> {
                    CourseModule courseModule = modelMapper.map(element, CourseModule.class);
                    courseModule.setModuleLessons(lessonService.getLessonMappedWithModule(courseModule, element.getModuleLessons()));
                    return courseModule.getCourseModuleToCourse(course);
                })
                .collect(Collectors.toList());
    }
}
