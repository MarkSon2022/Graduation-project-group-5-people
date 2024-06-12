package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.NoArgsConstructor;
import sse.edu.SPR2024.entity.Subscription;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionResponseDTO {
    private String id;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private BigDecimal boughtPrice;
    private Integer boughtMaxStudent;
    private String paymentUrl;
    private String status;
    private OrganizationResponseDTO org;
    private CoursePackageResponseSubscriptionDTO coursePackage;
}
