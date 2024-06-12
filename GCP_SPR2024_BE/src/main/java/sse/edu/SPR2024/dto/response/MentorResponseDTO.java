package sse.edu.SPR2024.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MentorResponseDTO {
    private String id;
    private String imgUrl;
    private String name;
    private String email;
    private UserMentorResponseDTO user;
}
