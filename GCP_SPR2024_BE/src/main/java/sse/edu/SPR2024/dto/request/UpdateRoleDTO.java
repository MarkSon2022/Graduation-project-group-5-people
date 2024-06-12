package sse.edu.SPR2024.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sse.edu.SPR2024.enums.UserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRoleDTO {
    private String email;
    private UserRole userRole;
}
