package sse.edu.SPR2024.dto.response;

import jakarta.persistence.Column;
import lombok.Data;
import sse.edu.SPR2024.dto.request.CourseRequestDTO;

import java.util.List;

@Data
public class ProjectResponseDTO {
    private String id;
    private String ageRecomment;
    private String goal;
    private String introVideoUrl;
    private String skill;
    private String status;
}
