package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.CourseModuleRequestDTO;
import sse.edu.SPR2024.dto.request.CoursePackageRequestDTO;
import sse.edu.SPR2024.dto.response.CourseModuleResponseDTO;
import sse.edu.SPR2024.entity.Course;
import sse.edu.SPR2024.entity.CourseModule;
import sse.edu.SPR2024.entity.CoursePackage;

import java.util.List;

public interface ICourseModuleService {
    List<CourseModule> getCourseModuleMappedWithCourse(Course course, List<CourseModuleRequestDTO> courseModuleRequestDTOs);
}
