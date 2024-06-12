package sse.edu.SPR2024.dto.request;

import lombok.Data;
import sse.edu.SPR2024.entity.Course;

import java.util.List;

@Data
public class ProjectRequestDTO {
    private String id;
    private String ageRecomment;
    private String goal;
    private String introVideoUrl;
    private String skill;
    private String status;
}
