package sse.edu.SPR2024.dto.request;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class LessonRequestDTO {
    private String id;
    private String description;
    private String title;
    private String videoUrl;
    private boolean isLock;
    private List<DocumentRequestDTO> lessonDocuments;
    private List<QuizRequestDTO> lessonQuizzes;
}
