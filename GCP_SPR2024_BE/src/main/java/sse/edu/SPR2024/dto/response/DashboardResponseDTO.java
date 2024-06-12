package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDTO {
    private Integer courseNumber;
    private Integer inActiveCourseNumber;
    private Integer learnerNumber;
    private Integer inActiveLearnerNumber;
    private Integer organizationNumber;
    private Integer inActiveOrganizationNumber;
    private Integer projectNumber;
    private Integer inActiveProjectNumber;
    private List<SubscriptionResponseDTO> subscription;
    private List<ProjectDashboardResponseDTO> project;
}
