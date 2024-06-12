package sse.edu.SPR2024.dto.request;

import lombok.Data;

@Data
public class DocumentRequestDTO {
    private Long id;
    private String description;
    private String documentUrl;
    private String title;
}
