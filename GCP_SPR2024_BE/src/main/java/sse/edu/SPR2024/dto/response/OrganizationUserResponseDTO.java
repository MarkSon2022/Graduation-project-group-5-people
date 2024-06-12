package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class OrganizationUserResponseDTO {
    private String id;
    private String orgName;
    private String orgLogoUrl;
}
