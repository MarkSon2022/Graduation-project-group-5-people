package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.LearningInformationCourseRequestDTO;
import sse.edu.SPR2024.dto.request.LearningInformationRequestDTO;
import sse.edu.SPR2024.dto.response.LearningInformationResponseDTO;
import sse.edu.SPR2024.entity.LearningInformation;

public interface ILearningInformationService {
    LearningInformationResponseDTO create(LearningInformationRequestDTO learningInformationRequestDTO);
    LearningInformationResponseDTO update(LearningInformationRequestDTO learningInformationRequestDTO);
    LearningInformationResponseDTO getLearningInformationById(String id);
    LearningInformationResponseDTO getLearningInfoByUser(LearningInformationCourseRequestDTO learningInformationCourseRequestDTO);
    LearningInformationResponseDTO getLearningInformationByLearnerAndGroup(LearningInformationRequestDTO learningInformationRequestDTO);
    LearningInformationResponseDTO getLearningInformationByGroupId(String groupId);
    LearningInformationResponseDTO getLearningInforByLearner(String id);
    LearningInformationResponseDTO updateLearnedLesson(LearningInformationRequestDTO learningInformationRequestDTO);

}
