package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.QuestionRequestDTO;
import sse.edu.SPR2024.dto.request.QuizRequestDTO;
import sse.edu.SPR2024.dto.response.QuestionResponseDTO;
import sse.edu.SPR2024.entity.CoursePackage;
import sse.edu.SPR2024.entity.Lesson;
import sse.edu.SPR2024.entity.Question;
import sse.edu.SPR2024.entity.Quiz;
import sse.edu.SPR2024.service.IAnswerService;
import sse.edu.SPR2024.service.IQuestionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final ModelMapper modelMapper;
    private final IAnswerService answerService;

    @Override
    public QuestionResponseDTO createQuestion(QuestionRequestDTO questionRequestDTO) {
        return null;
    }

    @Override
    public QuestionResponseDTO updateQuestion(QuestionRequestDTO questionRequestDTO) {
        return null;
    }

    @Override
    public List<QuestionResponseDTO> getQuestions() {
        return null;
    }

    @Override
    public QuestionResponseDTO getQuestionById(Long id) {
        return null;
    }

    @Override
    public List<Question> getQuestionMappedWithLesson(Quiz quiz, List<QuestionRequestDTO> questionRequestDTOS) {
        return questionRequestDTOS
                .stream()
                .map(element -> {
                    Question question = modelMapper.map(element, Question.class);
                    question.setQuestionAnswers(answerService.getAnswerMappedWithQuestion(question, element.getQuestionAnswers()));
                    question.setQuiz(quiz);
                    return question;
                })
                .collect(Collectors.toList());
    }
}
