package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class QuizDetailHiddenResponseDTO {
    private Long id;
    private Long duration;
    private String status;
    private Long passPercentage;
    private Long score;
    private String title;
    private List<QuestionHiddenResponseDTO> quizQuestions;
    private LessonQuizResponseDTO lesson;
}
