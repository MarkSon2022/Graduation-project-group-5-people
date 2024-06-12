package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseLessonResponseDTO {
    private String id;
    private String status;
    private String name;
    private Long duration;
    private String imgUrl;
    private String age;
    private String income;
    private String outcome;
}
