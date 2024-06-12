package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sse.edu.SPR2024.dto.response.SubscriptionPackageCourseInfoResponse;
import sse.edu.SPR2024.dto.response.SubscriptionResponseDTO;
import sse.edu.SPR2024.entity.Subscription;

import java.util.List;

public interface ISubscriptionRepository extends JpaRepository<Subscription, String> {
//    @Query(value = "SELECT s.id as id, s.start_date as startDate, s.end_date as endDate, s.bought_price as boughtPrice, s.bought_max_student as boughtMaxStudent, " +
//            "       s.created_date as createdDate, s.modified_date as modifiedDate, cp.name as packageName, c.name as courseName " +
//            "       FROM subscription s " +
//            "           INNER JOIN course_package cp on cp.id = s.package_id " +
//            "           INNER JOIN course c on c.id = cp.course_id " +
//            "       WHERE s.org_id = :orgId", nativeQuery = true)
//    List<SubscriptionPackageCourseInfoResponse> findByOrgId(String orgId);

    List<Subscription> findAllByOrgOrgId(String orgId);
    List<Subscription> findSubscriptionsByOrgOrgIdAndCoursePackage_Course_Id(String orgId, String courseId);
}
