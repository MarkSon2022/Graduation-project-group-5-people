package sse.edu.SPR2024.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearnerAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String id;

    @Column
    private String lessonId;

    @Column
    private String courseId;

    @Column
    private Integer correctAnswers;

    @Column(columnDefinition = "TEXT")
    private String answers;

    @Column
    private OffsetDateTime submitTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "learner_id", nullable = false)
    private Learner learner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
}
