package sse.edu.SPR2024.dto.request;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class CertificateRequestDTO {
    private Long id;
    private String certificateUrl;
    private String description;
    private String imgUrl;
    private String name;
    private LocalDateTime receiveDate;
}
