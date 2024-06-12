package sse.edu.SPR2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDTO {
    private Long id;
    private String description;
    private String type;
    private List<AnswerRequestDTO> questionAnswers;

    @Override
    public String toString() {
        return "QuestionRequestDTO{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", answerRequestDTOS=" + questionAnswers +
                '}';
    }
}
