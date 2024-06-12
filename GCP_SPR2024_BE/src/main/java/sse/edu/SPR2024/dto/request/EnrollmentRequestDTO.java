package sse.edu.SPR2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequestDTO {
    private String id;
    private String learnerId;
    private String courseId;
    private String status;
}
