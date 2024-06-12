package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.QuizRequestDTO;
import sse.edu.SPR2024.dto.response.LessonResponseDTO;
import sse.edu.SPR2024.dto.response.QuizDetailHiddenResponseDTO;
import sse.edu.SPR2024.dto.response.QuizDetailResponseDTO;
import sse.edu.SPR2024.dto.response.QuizResponseDTO;
import sse.edu.SPR2024.entity.Lesson;
import sse.edu.SPR2024.entity.Quiz;

import java.util.List;

public interface IQuizService {
    QuizDetailResponseDTO createQuiz(QuizRequestDTO quizRequestDTO);
    QuizDetailResponseDTO updateQuiz(QuizRequestDTO quizRequestDTO);
    QuizDetailResponseDTO deactivateQuiz(QuizRequestDTO quizRequestDTO);
    List<Quiz> getQuizMappedWithLesson(Lesson lesson, List<QuizRequestDTO> quizRequestDTOs);
    LessonResponseDTO getQuizByLessonId(String id);
    LessonResponseDTO getQuizValidByLessonId(String id);
    List<QuizDetailResponseDTO> getAllQuizzes();
    QuizDetailHiddenResponseDTO getQuizById(Long id);

}
