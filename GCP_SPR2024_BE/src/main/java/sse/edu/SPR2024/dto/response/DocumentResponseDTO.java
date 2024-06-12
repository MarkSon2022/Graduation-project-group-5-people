package sse.edu.SPR2024.dto.response;

import lombok.Data;

@Data
public class DocumentResponseDTO {
    private Long id;
    private String description;
    private String documentUrl;
    private String title;
}
