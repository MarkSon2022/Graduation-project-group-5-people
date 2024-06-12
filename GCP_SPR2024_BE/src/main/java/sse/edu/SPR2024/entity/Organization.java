package sse.edu.SPR2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization extends BaseEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private String orgId;

    @Column(nullable = false, updatable = false)
    private String orgName;

    @Column
    private String orgLogoUrl;

    @OneToMany(mappedBy = "organization")
    private Set<Learner> organizationLearners;

    @OneToMany(mappedBy = "organization")
    private Set<Mentor> organizationMentors;

    @OneToMany(mappedBy = "org")
    private Set<Subscription> orgSubscriptions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
