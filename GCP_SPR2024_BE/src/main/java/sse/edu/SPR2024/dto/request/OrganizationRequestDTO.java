package sse.edu.SPR2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDTO {
    private String id;
    private String orgName;
    private String orgLogoUrl;
    private UserRequestDTO user;
}
