package sse.edu.SPR2024.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CourseModuleRequestDTO {
    private String id;
    private String description;
    private String title;
    private List<LessonRequestDTO> moduleLessons;
}
