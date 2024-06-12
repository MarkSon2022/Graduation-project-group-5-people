package sse.edu.SPR2024.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Builder
public class SubscriptionPackageCourseInfoResponse {
    private String id;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private BigDecimal boughtPrice;
    private Integer boughtMaxStudent;
    private String packageName;
    private String courseName;
    private OffsetDateTime createdDate;
    private OffsetDateTime modifiedDate;

}
