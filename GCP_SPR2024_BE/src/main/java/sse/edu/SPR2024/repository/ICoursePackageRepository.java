package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.CoursePackage;

public interface ICoursePackageRepository extends JpaRepository<CoursePackage, String> {
}
