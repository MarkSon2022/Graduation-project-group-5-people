package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Answer;
import sse.edu.SPR2024.entity.CourseModule;

public interface IAnswerRepository extends JpaRepository<Answer, Long> {
}
