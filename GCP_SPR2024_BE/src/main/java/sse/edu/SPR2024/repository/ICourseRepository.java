package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Course;

import java.util.List;

public interface ICourseRepository extends JpaRepository<Course, String> {
    List<Course> findAllByNameContains(String name);
}
