package sse.edu.SPR2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Lesson extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String description;

    @Column
    private String title;

    @Column
    private String videoUrl;

    @Column
    private boolean isLock;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Document> lessonDocuments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private CourseModule courseModule;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Quiz> lessonQuizzes;

    public Lesson getLessonToModule(CourseModule module) {
        this.setCourseModule(module);
        return this;
    }
}
