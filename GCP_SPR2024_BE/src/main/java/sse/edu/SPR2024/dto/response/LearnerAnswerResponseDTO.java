package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microsoft.sql.DateTimeOffset;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LearnerAnswerResponseDTO {
    private String id;
    private String lessonId;
    private String courseId;
    private String answers;
    private String learnerId;
    private String score;
    private Long correctAnswers;
    private String quizId;
    private Boolean isRetake = false;
    private List<QuestionResponseDTO> answerData;
    private OffsetDateTime createdDate;
    private OffsetDateTime submitTime;
}
