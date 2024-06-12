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
public class Quiz extends BaseEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long duration;

    @Column
    private String title;

    @Column
    private String status;
    @Column
    private Long passPercentage;
    @Column
    private Long score;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> quizQuestions;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<LearnerAnswer> learnerAnswers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public Quiz getQuizToLesson(Lesson lesson) {
        this.setLesson(lesson);
        return this;
    }

}
