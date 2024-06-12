package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.DocumentRequestDTO;
import sse.edu.SPR2024.entity.CoursePackage;
import sse.edu.SPR2024.entity.Document;
import sse.edu.SPR2024.entity.Lesson;
import sse.edu.SPR2024.service.IDocumentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService implements IDocumentService {
    private final ModelMapper modelMapper;
    @Override
    public List<Document> getDocumentMappedWithLesson(Lesson lesson, List<DocumentRequestDTO> documentRequestDTOs) {
        return documentRequestDTOs
                .stream()
                .map(element -> {
                    Document document = modelMapper.map(element, Document.class);
                    return document.getDocumentToLesson(lesson);
                })
                .collect(Collectors.toList());
    }
}
