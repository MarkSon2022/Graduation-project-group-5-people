package sse.edu.SPR2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String ageRecomment;

    @Column
    private String goal;

    @Column
    private String introVideoUrl;

    @Column
    private String skill;

    @Column
    private String status;

    @ToString.Exclude
    @OneToMany(mappedBy = "project")
    private List<Course> projectCourses;
}
