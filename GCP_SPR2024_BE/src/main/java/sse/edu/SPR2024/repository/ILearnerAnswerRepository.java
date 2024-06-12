package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.LearnerAnswer;

public interface ILearnerAnswerRepository extends JpaRepository<LearnerAnswer, String> {
    LearnerAnswer findFirstByQuizIdAndLearnerIdOrderByCreatedDateDesc(Long quizId, String learnerId);
}
