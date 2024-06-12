package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.AnswerRequestDTO;
import sse.edu.SPR2024.entity.Answer;
import sse.edu.SPR2024.entity.Question;
import sse.edu.SPR2024.entity.Quiz;
import sse.edu.SPR2024.service.IAnswerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {
    private final ModelMapper modelMapper;
    @Override
    public List<Answer> getAnswerMappedWithQuestion(Question question, List<AnswerRequestDTO> answerRequestDTOS) {
        return answerRequestDTOS
                .stream()
                .map(element -> {
                    Answer answer = modelMapper.map(element, Answer.class);
                    answer.setQuestion(question);
                    return answer;
                })
                .collect(Collectors.toList());
    }
}
