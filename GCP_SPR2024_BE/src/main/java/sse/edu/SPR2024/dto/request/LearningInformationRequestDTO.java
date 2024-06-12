package sse.edu.SPR2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningInformationRequestDTO {
    private String id;
    private String status;
    private OffsetDateTime assignDate;
    private OffsetDateTime enrollDate;
    private String learnedLesson;
    private String courseId;
    private String learnerId;
    private String groupId;
}
