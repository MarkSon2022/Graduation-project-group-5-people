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
public class Mentor {
    @Id
    @Column(nullable = false, updatable = false)
    private String id;

    @Column
    private String imgUrl;

    @Column
    private String name;

    @Column
    private String email;

    @OneToMany(mappedBy = "mentor")
    private Set<Group> mentorGroups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
