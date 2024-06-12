package sse.edu.SPR2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.dto.request.CourseRequestDTO;
import sse.edu.SPR2024.dto.response.CourseResponseDTO;
import sse.edu.SPR2024.enums.CourseStatus;
import sse.edu.SPR2024.service.ICourseService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponseDTO> saveCourse(@RequestBody CourseRequestDTO course) {
        CourseResponseDTO savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CourseResponseDTO> updateCourse(@RequestBody CourseRequestDTO course) {
        CourseResponseDTO savedCourse = courseService.updateCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @PutMapping("/comment")
    public ResponseEntity<CourseResponseDTO> updateComment(@RequestBody CourseRequestDTO course) {
        CourseResponseDTO savedCourse = courseService.updateComment(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @PutMapping("/disable")
    public ResponseEntity<CourseResponseDTO> disableCourse(@RequestBody CourseRequestDTO course) {
        CourseResponseDTO savedCourse = courseService.disableCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @PutMapping("/approve")
    public ResponseEntity<CourseResponseDTO> approveCourse(@RequestBody CourseRequestDTO course) {
        course.setStatus(CourseStatus.ACTIVE.name());
        CourseResponseDTO savedCourse = courseService.approveCourse(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable String id) {
        CourseResponseDTO course = courseService.getCourseById(id);
        if (Objects.nonNull(course)) {
            return new ResponseEntity<>(course, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/valid")
    public ResponseEntity<List<CourseResponseDTO>> getAllValidCourses() {
        List<CourseResponseDTO> courses = courseService.getAllValidCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
