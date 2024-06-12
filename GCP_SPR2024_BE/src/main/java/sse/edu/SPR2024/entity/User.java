package sse.edu.SPR2024.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "\"User\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{

    @Id
    @Column(nullable = false, updatable = false)
    private String userId;

    @Column
    private String address;

    @Column
    private Integer age;

    @Column
    private LocalDateTime birthDate;

    @Column
    private String currentLoginAddress;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column
    private String gender;

    @Column
    private String password;

    @Column(nullable = false, length = 20)
    private String role;

    @Column(nullable = false)
    private Boolean status;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Learner learner;

    @OneToOne(mappedBy = "user",  cascade = CascadeType.ALL)
    private Mentor mentor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Organization organization;
}
