package sse.edu.SPR2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningInformationCourseRequestDTO {
    private String learnerId;
    private String courseId;
}
