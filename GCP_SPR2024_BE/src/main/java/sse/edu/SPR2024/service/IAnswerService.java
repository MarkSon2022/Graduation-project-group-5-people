package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.AnswerRequestDTO;
import sse.edu.SPR2024.dto.request.CoursePackageRequestDTO;
import sse.edu.SPR2024.entity.Answer;
import sse.edu.SPR2024.entity.Question;

import java.util.List;

public interface IAnswerService {
    public List<Answer> getAnswerMappedWithQuestion(Question question, List<AnswerRequestDTO> answerRequestDTOS);
}
