package sse.edu.SPR2024.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class LessonQuizResponseDTO {
    private String id;
    private String description;
    private String title;
    private CourseModuleQuizResponseDTO courseModule;
}
