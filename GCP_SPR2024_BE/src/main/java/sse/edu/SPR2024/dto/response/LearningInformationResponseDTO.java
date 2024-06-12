package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningInformationResponseDTO {
    private String id;
    private String status;
    private String assignDate;
    private String enrollDate;
    private String learnedLesson;
    private LearnerResponseDTO learner;
    private GroupResponseDTO group;
}
