package sse.edu.SPR2024.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private String id;

    @Column
    private String age;

    @Column
    private Long duration;

    @Column
    private String imgUrl;

    @Column
    private String name;

    @Column
    private String status;

    @Column
    private String description;

    @Column
    private String income;

    @Column
    private String outcome;

    @Column(columnDefinition = "NTEXT")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Enrollment> courseEnrollments;

    @ToString.Exclude
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseModule> courseModules;

    @ToString.Exclude
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CoursePackage> courseCoursePackages;
}
