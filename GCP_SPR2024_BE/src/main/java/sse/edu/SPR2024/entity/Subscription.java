package sse.edu.SPR2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String id;

    @Column
    private OffsetDateTime startDate;

    @Column
    private OffsetDateTime endDate;

    @Column(precision = 18, scale = 2)
    private BigDecimal boughtPrice;

    @Column
    private Integer boughtMaxStudent;

    @Column
    private String status;

    @OneToMany(mappedBy = "subscription")
    private Set<Group> subscriptionGroups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private Organization org;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id")
    private CoursePackage coursePackage;
}
