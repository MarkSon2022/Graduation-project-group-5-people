package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.CoursePackageRequestDTO;
import sse.edu.SPR2024.dto.response.CoursePackageResponseDTO;
import sse.edu.SPR2024.entity.Course;
import sse.edu.SPR2024.entity.CoursePackage;

import java.util.List;

public interface ICoursePackageService {
    CoursePackageResponseDTO saveCoursePackage(CoursePackageRequestDTO coursePackageRequestDTO);
    List<CoursePackageResponseDTO> saveListCoursePackage(Course course, List<CoursePackageRequestDTO> coursePackageRequestDTOs);
    List<CoursePackage> getCoursePackageMappedWithCourse(Course course, List<CoursePackageRequestDTO> coursePackageRequestDTOs);
}
