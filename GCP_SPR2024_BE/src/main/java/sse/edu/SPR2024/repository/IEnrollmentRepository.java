package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Enrollment;

import java.util.List;

public interface IEnrollmentRepository extends JpaRepository<Enrollment, String> {
    List<Enrollment> getEnrollmentsByLearner_Id(String learnerId);
    List<Enrollment> getEnrollmentsByCourse_Id(String courseId);
    Enrollment findFirstByCourseIdAndLearnerId(String courseId, String learnerId);
    Enrollment findFirstByCourseIdAndLearnerIdAndStatus(String courseId, String learnerId, String status);
}
