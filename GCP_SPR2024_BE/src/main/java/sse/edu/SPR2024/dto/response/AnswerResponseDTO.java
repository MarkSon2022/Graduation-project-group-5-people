package sse.edu.SPR2024.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AnswerResponseDTO {
    private Long id;
    private String description;
    private Boolean isCorrect;
}
