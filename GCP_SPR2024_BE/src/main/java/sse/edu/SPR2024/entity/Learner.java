package sse.edu.SPR2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Learner {

    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column
    private String avatarUrl;

    @Column(nullable = false)
    private Long point;

    @Column(nullable = false)
    private Integer ranking;

    @OneToMany(mappedBy = "learner")
    private Set<Enrollment> learnerEnrollments;

    @OneToMany(mappedBy = "learner")
    private List<LearnerAnswer> learnerAnswers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
    private Set<LearningInformation> learnerLearningInformations;

    public void setLearnerLearningInformations(
            final Set<LearningInformation> learnerLearningInformations) {
        this.learnerLearningInformations = learnerLearningInformations;
    }

}
