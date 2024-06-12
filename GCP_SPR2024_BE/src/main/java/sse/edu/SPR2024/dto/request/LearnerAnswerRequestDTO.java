package sse.edu.SPR2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microsoft.sql.DateTimeOffset;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearnerAnswerRequestDTO {
    private String id;
    private String lessonId;
    private String courseId;
    private String answers;
    private String learnerId;
    private String quizId;
    private String createdDate;
}
