package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.CourseRequestDTO;
import sse.edu.SPR2024.dto.request.SearchCourseRequestDTO;
import sse.edu.SPR2024.dto.response.CourseResponseDTO;
import sse.edu.SPR2024.entity.Course;
import sse.edu.SPR2024.entity.Project;

import java.util.List;

public interface ICourseService {
    CourseResponseDTO saveCourse(CourseRequestDTO course);

    CourseResponseDTO updateCourse(CourseRequestDTO course);
    CourseResponseDTO approveCourse(CourseRequestDTO course);
    CourseResponseDTO disableCourse(CourseRequestDTO course);
    CourseResponseDTO updateStatus(CourseRequestDTO course);

    CourseResponseDTO getCourseById(String id);

    List<CourseResponseDTO> getAllCourses();
    List<CourseResponseDTO> getAllValidCourses();

    void deleteCourse(String id);
    List<Course> getCourseMappedWithProject(Project project, List<CourseRequestDTO> courseRequestDTOs);
    CourseResponseDTO updateComment(CourseRequestDTO courseRequestDTO);
}
