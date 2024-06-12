package sse.edu.SPR2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.dto.request.LearningInformationCourseRequestDTO;
import sse.edu.SPR2024.dto.request.LearningInformationRequestDTO;
import sse.edu.SPR2024.dto.response.LearningInformationResponseDTO;
import sse.edu.SPR2024.service.ILearningInformationService;

@RestController
@RequestMapping("/api/v1/learning-info")
@RequiredArgsConstructor
public class LearningInfoController {
    private final ILearningInformationService learningInfoService;

    @PostMapping
    public ResponseEntity<LearningInformationResponseDTO> createLearningInfo(@RequestBody LearningInformationRequestDTO learningInfoRequestDTO) {
        LearningInformationResponseDTO response = learningInfoService.getLearningInformationByLearnerAndGroup(learningInfoRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LearningInformationResponseDTO> getLearningInfoByUserId(@PathVariable String id) {
        LearningInformationResponseDTO response = learningInfoService.getLearningInforByLearner(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/lesson")
    public ResponseEntity<LearningInformationResponseDTO> updateLearnedLesson(@RequestBody LearningInformationRequestDTO learningInfoRequestDTO) {
        LearningInformationResponseDTO response = learningInfoService.updateLearnedLesson(learningInfoRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/course")
    public ResponseEntity<LearningInformationResponseDTO> getLearningInfoByUserCourse(@RequestBody LearningInformationCourseRequestDTO learningInformationCourseRequestDTO) {
        LearningInformationResponseDTO response = learningInfoService.getLearningInfoByUser(learningInformationCourseRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
