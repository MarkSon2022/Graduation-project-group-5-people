package sse.edu.SPR2024.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.entity.Lesson;

public interface ILessonRepository extends JpaRepository<Lesson, String> {
    Lesson getById(String Id);
}
