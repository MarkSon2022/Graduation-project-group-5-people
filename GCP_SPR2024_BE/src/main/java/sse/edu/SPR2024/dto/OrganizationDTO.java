package sse.edu.SPR2024.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDTO {
    private String id;
    private String logoUrl;
    private String name;
    @NonNull
    private String moderatorId;
}
