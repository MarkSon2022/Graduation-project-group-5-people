package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.QuizRequestDTO;
import sse.edu.SPR2024.dto.response.*;
import sse.edu.SPR2024.entity.Document;
import sse.edu.SPR2024.entity.LearningInformation;
import sse.edu.SPR2024.entity.Lesson;
import sse.edu.SPR2024.entity.Quiz;
import sse.edu.SPR2024.enums.QuizStatus;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.ILessonRepository;
import sse.edu.SPR2024.repository.IQuizRepository;
import sse.edu.SPR2024.service.IQuestionService;
import sse.edu.SPR2024.service.IQuizService;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService implements IQuizService {
    private final ModelMapper modelMapper;
    private final ILessonRepository lessonRepository;
    private final IQuizRepository quizRepository;
    private final IQuestionService questionService;

    @Override
    public QuizDetailResponseDTO createQuiz(QuizRequestDTO quizRequestDTO) {
        Lesson lesson = lessonRepository.findById(quizRequestDTO.getLessonId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Lesson not found with id: {0}", quizRequestDTO.getLessonId())));

        Quiz quiz = modelMapper.map(quizRequestDTO, Quiz.class);
        quiz.setLesson(lesson);
        quiz.setStatus(QuizStatus.ACTIVE.name());
        quiz.setQuizQuestions(questionService.getQuestionMappedWithLesson(quiz, quizRequestDTO.getQuizQuestions()));
        return modelMapper.map(quizRepository.save(quiz), QuizDetailResponseDTO.class);
    }

    @Override
    public QuizDetailResponseDTO updateQuiz(QuizRequestDTO quizRequestDTO) {
        Quiz quiz = modelMapper.map(quizRequestDTO, Quiz.class);
        Quiz savedQuiz = quizRepository.save(quiz);
        return modelMapper.map(savedQuiz, QuizDetailResponseDTO.class);
    }

    @Override
    public QuizDetailResponseDTO deactivateQuiz(QuizRequestDTO quizRequestDTO) {
        Quiz quiz = quizRepository.findById(quizRequestDTO.getId())
                .orElseThrow(() -> new ServiceDataException("Quiz not found"));
                quiz.setStatus(QuizStatus.INACTIVE.name());
        return modelMapper.map(quizRepository.save(quiz), QuizDetailResponseDTO.class);
    }

    @Override
    public List<Quiz> getQuizMappedWithLesson(Lesson lesson, List<QuizRequestDTO> quizRequestDTOs) {
        return quizRequestDTOs
                .stream()
                .map(element -> {
                    Quiz quiz = modelMapper.map(element, Quiz.class);
                    return quiz.getQuizToLesson(lesson);
                })
                .collect(Collectors.toList());
    }
    @Override
    public LessonResponseDTO getQuizByLessonId(String id) {

        Lesson Lesson = lessonRepository.getById(id);
        if (Objects.isNull(Lesson)) {
            throw new ServiceDataException(MessageFormat.format("Lesson not found with learnerId: {0} ",id));

        }
        return modelMapper.map(Lesson, LessonResponseDTO.class);
    }

    @Override
    public LessonResponseDTO getQuizValidByLessonId(String id) {

        Lesson Lesson = lessonRepository.getById(id);
        if (Objects.isNull(Lesson)) {
            throw new ServiceDataException(MessageFormat.format("Lesson not found with learnerId: {0} ",id));
        }

        LessonResponseDTO lessonRes = modelMapper.map(Lesson, LessonResponseDTO.class);

        List<QuizResponseDTO> activeQuizResponseDTOs = lessonRes.getLessonQuizzes().stream()
                .filter(quiz -> quiz.getStatus().equals(QuizStatus.ACTIVE.name()))
                .collect(Collectors.toList());

        lessonRes.setLessonQuizzes(activeQuizResponseDTOs);
        return lessonRes;
    }

    @Override
    public List<QuizDetailResponseDTO> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();

        return quizzes
                .stream()
                .map(quiz -> modelMapper.map(quiz, QuizDetailResponseDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public QuizDetailHiddenResponseDTO getQuizById(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if (quiz.isPresent()) {
            return modelMapper.map(quiz.get(), QuizDetailHiddenResponseDTO.class);
        }
        return null;
    }
}
