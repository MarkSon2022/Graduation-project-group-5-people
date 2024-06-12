package sse.edu.SPR2024.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.LearnerAnswerRequestDTO;
import sse.edu.SPR2024.dto.response.AnswerResponseDTO;
import sse.edu.SPR2024.dto.response.LearnerAnswerResponseDTO;
import sse.edu.SPR2024.dto.response.QuestionResponseDTO;
import sse.edu.SPR2024.entity.*;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.IAnswerRepository;
import sse.edu.SPR2024.repository.ILearnerAnswerRepository;
import sse.edu.SPR2024.repository.ILearnerRepository;
import sse.edu.SPR2024.repository.IQuizRepository;
import sse.edu.SPR2024.service.ILearnerAnswerService;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LearnerAnswerService implements ILearnerAnswerService {
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ILearnerAnswerRepository learnerAnswerRepository;
    private final IAnswerRepository answerRepository;
    private final ILearnerRepository learnerRepository;
    private final IQuizRepository quizRepository;
    @Override
    public LearnerAnswerResponseDTO saveLearnerAnswer(LearnerAnswerRequestDTO requestDTO) {
// Fetch quiz from repository
        // Fetch quiz from repository
        Quiz quiz = quizRepository.findById(Long.parseLong(requestDTO.getQuizId())).orElseThrow(() -> new ServiceDataException("Quiz not found"));
        Learner learner = learnerRepository.findById(requestDTO.getLearnerId()).orElseThrow(() -> new ServiceDataException("Learner not found"));

        LearnerAnswer learnerAnswer = modelMapper.map(requestDTO, LearnerAnswer.class);
        try {
            // Deserialize JSON string into List<Map<String, Object>>
            List<Map<String, Object>> answersList = objectMapper.readValue(requestDTO.getAnswers(), new TypeReference<List<Map<String, Object>>>() {
            });

            int correctAnswers = 0;
            // Iterate over each answer provided by the learner
            for (Map<String, Object> answerMap : answersList) {
                Long questionId = ((Integer) answerMap.get("questionId")).longValue(); // Cast to Long if necessary
                List<Integer> answerIdsRaw = (List<Integer>) answerMap.get("answerIds");
                List<Long> answerIds = answerIdsRaw.stream().map(Long::valueOf).toList();
                String type = (String) answerMap.get("type");

                // Find the corresponding question entity
                Question question = quiz.getQuizQuestions()
                        .stream()
                        .filter(q -> q.getId().equals(questionId))
                        .findFirst()
                        .orElse(null);

                if (question != null) {
                    String answers = question.getQuestionAnswers()
                            .stream()
                            .filter(a -> a.getIsCorrect() == true)
                            .map(Answer::getId)
                            .sorted()
                            .toList().toString();

                    String compareAnswer = answerIds
                            .stream()
                            .sorted()
                            .toList().toString();

                    // If the answer is correct, increment the count of correct answers
                    if (answers.equals(compareAnswer)) {
                        correctAnswers++;
                    }
                }
            }

            // Save learner's answers and number of correct answers
            learnerAnswer.setQuiz(quiz);
            learnerAnswer.setLearner(learner);
            learnerAnswer.setCreatedDate(OffsetDateTime.parse(requestDTO.getCreatedDate()));
            learnerAnswer.setCorrectAnswers(correctAnswers);
            learnerAnswer.setSubmitTime(OffsetDateTime.now());
            learnerAnswerRepository.save(learnerAnswer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelMapper.map(learnerAnswer, LearnerAnswerResponseDTO.class);

    }

    @Override
    public LearnerAnswerResponseDTO getLearnerAnswerByQuizAndLearner(LearnerAnswerRequestDTO learnerAnswerRequestDTO) {
        LearnerAnswer learnerAnswer = learnerAnswerRepository
                .findFirstByQuizIdAndLearnerIdOrderByCreatedDateDesc(Long.valueOf(learnerAnswerRequestDTO.getQuizId()), learnerAnswerRequestDTO.getLearnerId());

        if (Objects.nonNull(learnerAnswer)) {
            LearnerAnswerResponseDTO learnerAnswerResponseDTO = modelMapper
                    .map(learnerAnswer, LearnerAnswerResponseDTO.class);
            Quiz quiz = quizRepository.findById(Long.parseLong(learnerAnswerRequestDTO.getQuizId()))
                    .orElseThrow(() -> new ServiceDataException("Quiz not found"));
            Double score = (double) learnerAnswer.getCorrectAnswers() / quiz.getQuizQuestions().size() * quiz.getScore();
            String scoreValue = String.format("%.2f", score);
            learnerAnswerResponseDTO.setScore(scoreValue);

            if (score < (double) (quiz.getPassPercentage() * quiz.getScore()) / 100) {
                learnerAnswerResponseDTO.setIsRetake(true);
            }

            List<QuestionResponseDTO> answerData = quiz.getQuizQuestions()
                    .stream()
                    .map(q -> {
                        QuestionResponseDTO questionResponseDTO = modelMapper.map(q, QuestionResponseDTO.class);
                        return questionResponseDTO;
                    })
                    .collect(Collectors.toList());

            learnerAnswerResponseDTO.setAnswerData(answerData);
            return learnerAnswerResponseDTO;
        } else {
            return null;
        }
    }
}
