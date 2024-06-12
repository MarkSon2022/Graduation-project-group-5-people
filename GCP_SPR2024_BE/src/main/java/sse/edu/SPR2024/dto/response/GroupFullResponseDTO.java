package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GroupFullResponseDTO {
    private String id;
    private String status;
    private String name;
    private Date createdDate;
    private Date modifiedDate;
    private String studentExist;
    private String courseName;
    private List<LearningEnrollmentResponseDTO> learningInformations;
}
