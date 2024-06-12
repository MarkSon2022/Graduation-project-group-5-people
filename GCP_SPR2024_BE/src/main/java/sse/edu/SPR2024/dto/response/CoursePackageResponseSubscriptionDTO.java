package sse.edu.SPR2024.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CoursePackageResponseSubscriptionDTO {
    private String name;
    private CourseResponseSubscriptionDTO course;
}
