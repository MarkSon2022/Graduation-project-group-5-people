package sse.edu.SPR2024.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoursePackageResponseDTO {
    private String id;
    private String name;
    private Integer maxStudent;
    private BigDecimal price;
}
