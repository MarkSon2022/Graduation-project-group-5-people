package sse.edu.SPR2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequestDTO {
    private Long Id;
    private String description;
    private Boolean isCorrect;

    @Override
    public String toString() {
        return "AnswerRequestDTO{" +
                "description='" + description + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
