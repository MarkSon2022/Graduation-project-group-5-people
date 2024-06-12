package sse.edu.SPR2024.dto.response;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GroupResponseDTO {
    private String id;
    private String status;
    private String name;
    private Date createdDate;
    private Date modifiedDate;
    private String studentExist;
    private SubscriptionResponseDTO subscription;
    private LearningInformationGroupResponseDTO learningInformations;
}
