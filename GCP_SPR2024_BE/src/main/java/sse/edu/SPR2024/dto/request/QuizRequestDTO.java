package sse.edu.SPR2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sse.edu.SPR2024.entity.Question;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequestDTO {
    private Long id;
    private Long duration;
    private Long passPercentage;
    private Long score;
    private String title;
    private String status;
    private String lessonId;
    private List<QuestionRequestDTO> quizQuestions;
}
