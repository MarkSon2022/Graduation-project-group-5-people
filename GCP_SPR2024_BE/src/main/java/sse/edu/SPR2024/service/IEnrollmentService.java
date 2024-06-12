package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.EnrollmentRequestDTO;
import sse.edu.SPR2024.dto.response.EnrollmentResponseDTO;

import java.util.List;

public interface IEnrollmentService {
    EnrollmentResponseDTO enroll(EnrollmentRequestDTO enrollmentRequestDTO);
    List<EnrollmentResponseDTO> getEnrollmentsByLearnerId(String learnerId);
    List<EnrollmentResponseDTO> getEnrollmentsByCourseId(String courseId);
    EnrollmentResponseDTO updateEnrollment(EnrollmentRequestDTO enrollmentRequestDTO);
    EnrollmentResponseDTO getById(String id);

}
