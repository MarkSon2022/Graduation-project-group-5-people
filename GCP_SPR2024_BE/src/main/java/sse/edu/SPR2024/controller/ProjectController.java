package sse.edu.SPR2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.dto.request.ProjectRequestDTO;
import sse.edu.SPR2024.dto.response.ProjectResponseDTO;
import sse.edu.SPR2024.service.IProjectService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final IProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> saveProject(@RequestBody ProjectRequestDTO project) {
        ProjectResponseDTO savedProject = projectService.createProject(project);
        return new ResponseEntity<>(savedProject, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProjectResponseDTO> updateProject(@RequestBody ProjectRequestDTO project) {
        ProjectResponseDTO savedProject = projectService.updateProject(project);
        if (Objects.isNull(savedProject)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getProjectById(@PathVariable String id) {
        ProjectResponseDTO project = projectService.getProjectById(id);
        if (Objects.nonNull(project)) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> Projects = projectService.getProjects();
        return new ResponseEntity<>(Projects, HttpStatus.OK);
    }
}

