package sse.edu.SPR2024.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoursePackageRequestDTO {
    private String id;
    private String name;
    private Integer maxStudent;
    private BigDecimal price;
}
