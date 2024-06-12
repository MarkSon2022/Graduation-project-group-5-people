package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.AnswerRequestDTO;
import sse.edu.SPR2024.dto.request.QuestionRequestDTO;
import sse.edu.SPR2024.dto.request.QuizRequestDTO;
import sse.edu.SPR2024.dto.response.QuestionResponseDTO;
import sse.edu.SPR2024.entity.Answer;
import sse.edu.SPR2024.entity.Lesson;
import sse.edu.SPR2024.entity.Question;
import sse.edu.SPR2024.entity.Quiz;

import java.util.List;

public interface IQuestionService {
    QuestionResponseDTO createQuestion(QuestionRequestDTO questionRequestDTO);
    QuestionResponseDTO updateQuestion(QuestionRequestDTO questionRequestDTO);
    List<QuestionResponseDTO> getQuestions();
    QuestionResponseDTO getQuestionById(Long id);
    List<Question> getQuestionMappedWithLesson(Quiz quiz, List<QuestionRequestDTO> questionRequestDTOS);

}
