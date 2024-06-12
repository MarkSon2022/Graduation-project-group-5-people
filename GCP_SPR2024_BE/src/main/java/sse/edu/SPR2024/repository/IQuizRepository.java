package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Quiz;

public interface IQuizRepository extends JpaRepository<Quiz, Long> {
}
