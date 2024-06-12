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
public class UserResponseDTO {
    private String userId;
    private String address;
    private Integer age;
    private LocalDateTime birthDate;
    private String email;
    private String fullName;
    private String photoURL;
    private String gender;
    private String role;
    private Boolean status;
    private String token;
    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;
    private OrganizationUserResponseDTO organization;
    private MentorUserResponseDTO mentor;
}
