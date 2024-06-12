package sse.edu.SPR2024.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sse.edu.SPR2024.dto.request.LearnerAnswerRequestDTO;
import sse.edu.SPR2024.dto.response.LearnerAnswerResponseDTO;
import sse.edu.SPR2024.service.ILearnerAnswerService;

import javax.swing.text.html.parser.Entity;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/learner-answer")
@RequiredArgsConstructor
public class LearnerAnswerController {
    private final ILearnerAnswerService learnerAnswerService;

    @PostMapping
    public ResponseEntity<LearnerAnswerResponseDTO> createOrUpdateLearnerAnswer(@RequestBody LearnerAnswerRequestDTO learnerAnswerRequestDTO) {
       LearnerAnswerResponseDTO learnerAnswerResponseDTO = learnerAnswerService
               .saveLearnerAnswer(learnerAnswerRequestDTO);
        if(Objects.nonNull(learnerAnswerResponseDTO)){
            return new ResponseEntity<>(learnerAnswerResponseDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/check-valid")
    public ResponseEntity<LearnerAnswerResponseDTO> getLearnerAnswerByQuizAndLearner(@RequestBody LearnerAnswerRequestDTO learnerAnswerRequestDTO) {
        LearnerAnswerResponseDTO learnerAnswerResponseDTO = learnerAnswerService
                .getLearnerAnswerByQuizAndLearner(learnerAnswerRequestDTO);
        if(Objects.nonNull(learnerAnswerResponseDTO)){
            return new ResponseEntity<>(learnerAnswerResponseDTO, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
