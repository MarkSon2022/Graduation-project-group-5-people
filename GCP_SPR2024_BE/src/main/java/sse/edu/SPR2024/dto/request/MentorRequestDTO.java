package sse.edu.SPR2024.dto.request;

import lombok.Data;

@Data
public class MentorRequestDTO {
    private String id;
    private String imgUrl;
    private String name;
    private String email;
    private String orgId;
}
