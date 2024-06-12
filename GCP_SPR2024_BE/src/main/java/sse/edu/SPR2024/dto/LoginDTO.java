package sse.edu.SPR2024.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
    private String accessToken;
}
