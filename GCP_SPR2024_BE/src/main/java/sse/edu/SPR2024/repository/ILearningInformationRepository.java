package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.LearningInformation;

import java.util.List;

public interface ILearningInformationRepository extends JpaRepository<LearningInformation, String> {
    LearningInformation findFirstByLearner_IdAndGroup_Id(String learnerId, String groupId);
    LearningInformation findLearningInformationByGroup_Id(String groupId);
    List<LearningInformation> findAllByGroupId(String groupId);
    List<LearningInformation> findLearningInformationByGroupSubscriptionId(String subscriptionId);
    int countLearningInformationsByGroupId(String groupId);
    LearningInformation findByLearnerId(String learnerId);
    LearningInformation findFirstByLearnerIdAndCourseId(String learnerId, String courseId);
    LearningInformation findFirstByLearnerIdAndCourseIdAndStatus(String learnerId, String courseId, String status);
    LearningInformation findFirstByLearnerIdOrderByAssignDateDesc(String learnerId);
}
