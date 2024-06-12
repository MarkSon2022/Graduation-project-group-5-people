package sse.edu.SPR2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.dto.request.EnrollmentRequestDTO;
import sse.edu.SPR2024.dto.response.EnrollmentResponseDTO;
import sse.edu.SPR2024.service.IEnrollmentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final IEnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enroll(@RequestBody EnrollmentRequestDTO enrollmentRequestDTO) {
        return ResponseEntity.ok(enrollmentService.enroll(enrollmentRequestDTO));
    }

    @GetMapping("/learner/{learnerId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentsByLearnerId(@PathVariable String learnerId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByLearnerId(learnerId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getEnrollmentsByCourseId(String courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourseId(courseId));
    }

    @PutMapping
    public ResponseEntity<EnrollmentResponseDTO> updateEnrollment(EnrollmentRequestDTO enrollmentRequestDTO) {
        return ResponseEntity.ok(enrollmentService.updateEnrollment(enrollmentRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(@PathVariable String id) {
        return ResponseEntity.ok(enrollmentService.getById(id));
    }
}
