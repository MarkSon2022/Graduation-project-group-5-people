package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Question;

public interface IQuestionRepository extends JpaRepository<Question, Long>{
}
