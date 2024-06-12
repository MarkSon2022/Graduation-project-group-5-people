package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.ProjectRequestDTO;
import sse.edu.SPR2024.dto.response.ProjectResponseDTO;

import java.util.List;

public interface IProjectService {
    ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);
    ProjectResponseDTO updateProject(ProjectRequestDTO projectRequestDTO);
    List<ProjectResponseDTO> getProjects();
    ProjectResponseDTO getProjectById(String id);
}
