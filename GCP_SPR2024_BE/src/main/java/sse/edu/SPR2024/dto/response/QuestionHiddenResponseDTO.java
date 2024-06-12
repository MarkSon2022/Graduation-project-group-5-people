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
public class QuestionHiddenResponseDTO {

    private Long id;
    private String description;
    private String type;
    private List<AnswerHiddenResponseDTO> questionAnswers;
}
