package sse.edu.SPR2024.dto.response;

import lombok.Data;
import sse.edu.SPR2024.dto.request.LessonRequestDTO;

import java.util.List;

@Data
public class CourseModuleResponseDTO {
    private String id;
    private String description;
    private String title;
    private List<LessonResponseDTO> moduleLessons;
}
