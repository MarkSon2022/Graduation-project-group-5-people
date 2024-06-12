package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Learner;

public interface ILearnerRepository extends JpaRepository<Learner, String> {
}
