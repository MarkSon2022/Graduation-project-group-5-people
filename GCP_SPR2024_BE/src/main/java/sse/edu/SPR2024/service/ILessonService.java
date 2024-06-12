package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.LessonRequestDTO;
import sse.edu.SPR2024.entity.CourseModule;
import sse.edu.SPR2024.entity.Lesson;

import java.util.List;

public interface ILessonService {
    List<Lesson> getLessonMappedWithModule(CourseModule module, List<LessonRequestDTO> lessonRequestDTOs);
}
