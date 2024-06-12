package sse.edu.SPR2024.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {
    private String id;
    private String name;
    private Long duration;
    private String imgUrl;
    private String status;
    private String age;
    private String projectId;
    private String module;
    private String description;
    private String income;
    private String outcome;
    private String comment;
    private List<CourseModuleRequestDTO> courseModules;
    private List<CoursePackageRequestDTO> courseCoursePackages;
}
