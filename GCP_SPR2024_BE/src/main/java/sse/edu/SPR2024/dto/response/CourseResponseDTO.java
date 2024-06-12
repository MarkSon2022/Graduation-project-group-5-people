package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseResponseDTO {
    private String id;
    private String status;
    private String name;
    private Long duration;
    private String imgUrl;
    private String age;
    private String income;
    private String outcome;
    private String comment;
    private ProjectResponseDTO project;
    private String description;
    private OffsetDateTime modifiedDate;
    private List<CourseModuleResponseDTO> courseModules;
    private List<CoursePackageResponseDTO> courseCoursePackages;
}
