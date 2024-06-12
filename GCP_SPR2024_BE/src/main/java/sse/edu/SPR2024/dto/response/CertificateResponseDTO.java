package sse.edu.SPR2024.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sse.edu.SPR2024.entity.Learner;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertificateResponseDTO {
    private LocalDateTime receiveDate;
    private CourseResponseSubscriptionDTO course;
    private LearnerResponseDTO learner;
}
