package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.DocumentRequestDTO;
import sse.edu.SPR2024.dto.request.QuizRequestDTO;
import sse.edu.SPR2024.entity.Document;
import sse.edu.SPR2024.entity.Lesson;
import sse.edu.SPR2024.entity.Quiz;

import java.util.List;

public interface IDocumentService {
    List<Document> getDocumentMappedWithLesson(Lesson lesson, List<DocumentRequestDTO> documentRequestDTOs);
}
