package sse.edu.SPR2024.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionRequestDTO {
    private String id;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private BigDecimal boughtPrice;
    private Integer boughtMaxStudent;
    private String status;
    private String coursePackageId;
    private String organizationId;
}
