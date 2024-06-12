package sse.edu.SPR2024.dto.response;

import lombok.Data;
import sse.edu.SPR2024.dto.request.DocumentRequestDTO;
import sse.edu.SPR2024.dto.request.QuizRequestDTO;

import java.util.List;

@Data
public class LessonResponseDTO {
    private String id;
    private String description;
    private String title;
    private String videoUrl;
    private boolean isLock;
    private List<DocumentResponseDTO> lessonDocuments;
    private List<QuizResponseDTO> lessonQuizzes;
}
