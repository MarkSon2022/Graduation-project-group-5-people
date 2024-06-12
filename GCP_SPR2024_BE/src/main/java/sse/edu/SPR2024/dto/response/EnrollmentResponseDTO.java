package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sse.edu.SPR2024.entity.Certificate;
import sse.edu.SPR2024.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDTO {
    private String id;
    private String status;
    private UserBasicResponseDTO user;
    private CourseResponseSubscriptionDTO course;
    private Certificate certificate;
}
