package sse.edu.SPR2024.dto.request;

import lombok.Data;

@Data
public class SearchCourseRequestDTO {
    private String courseName;
    private String skill;
    private String status;
    private String age;
    private int duration;
}
