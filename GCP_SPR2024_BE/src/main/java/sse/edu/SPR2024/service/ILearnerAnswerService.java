package sse.edu.SPR2024.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.dto.request.LearnerAnswerRequestDTO;
import sse.edu.SPR2024.dto.response.LearnerAnswerResponseDTO;
import sse.edu.SPR2024.entity.Learner;
import sse.edu.SPR2024.entity.LearnerAnswer;

public interface ILearnerAnswerService {
    LearnerAnswerResponseDTO saveLearnerAnswer(LearnerAnswerRequestDTO learnerAnswerRequestDTO);
    LearnerAnswerResponseDTO getLearnerAnswerByQuizAndLearner(LearnerAnswerRequestDTO learnerAnswerRequestDTO);
}
