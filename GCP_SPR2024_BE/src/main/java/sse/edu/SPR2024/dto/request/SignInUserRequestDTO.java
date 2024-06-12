package sse.edu.SPR2024.dto.request;

import lombok.Data;

@Data
public class SignInUserRequestDTO {
    private String email;
    private String googleId;
    private String name;
    private String imageUrl;
}
