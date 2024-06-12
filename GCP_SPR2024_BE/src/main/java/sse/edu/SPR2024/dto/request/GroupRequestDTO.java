package sse.edu.SPR2024.dto.request;

import lombok.*;
import sse.edu.SPR2024.entity.LearningInformation;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRequestDTO {
    private String id;
    private String status;
    private String name;
    private String subscriptionId;
    private String mentorId;
    private List<String> learnerEmail;
}
