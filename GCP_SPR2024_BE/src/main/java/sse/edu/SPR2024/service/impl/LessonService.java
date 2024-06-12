package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.LessonRequestDTO;
import sse.edu.SPR2024.entity.CourseModule;
import sse.edu.SPR2024.entity.Lesson;
import sse.edu.SPR2024.service.ILessonService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService {
    private final ModelMapper modelMapper;
    private final DocumentService documentService;
    private final QuizService quizService;

    @Override
    public List<Lesson> getLessonMappedWithModule(CourseModule module, List<LessonRequestDTO> lessonRequestDTOs) {
        return lessonRequestDTOs
                .stream()
                .map(element -> {
                    Lesson lesson = modelMapper.map(element, Lesson.class);
                    lesson.setLessonQuizzes(quizService.getQuizMappedWithLesson(lesson, element.getLessonQuizzes()));
                    lesson.setLessonDocuments(documentService.getDocumentMappedWithLesson(lesson, element.getLessonDocuments()));
                    return lesson.getLessonToModule(module);
                })
                .collect(Collectors.toList());
    }
}
