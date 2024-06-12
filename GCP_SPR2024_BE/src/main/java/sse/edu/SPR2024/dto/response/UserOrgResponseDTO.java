package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserOrgResponseDTO {
    private String userId;
    private String address;
    private Integer age;
    private LocalDateTime birthDate;
    private String email;
    private String fullName;
    private String gender;
    private String role;
    private Boolean status;
    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;
}
