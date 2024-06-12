package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import sse.edu.SPR2024.dto.request.ProjectRequestDTO;
import sse.edu.SPR2024.dto.response.ProjectResponseDTO;
import sse.edu.SPR2024.entity.Project;
import sse.edu.SPR2024.enums.ProjectStatus;
import sse.edu.SPR2024.repository.IProjectRepository;
import sse.edu.SPR2024.service.IProjectService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService {
    private final ModelMapper modelMapper;
    private final IProjectRepository projectRepository;
    private final CourseService courseService;

    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
        Project projectSave = modelMapper.map(projectRequestDTO, Project.class);
        projectSave.setStatus(ProjectStatus.ACTIVE.name());

        return modelMapper.map(projectRepository.save(projectSave), ProjectResponseDTO.class);
    }

    @Override
    public ProjectResponseDTO updateProject(ProjectRequestDTO projectRequestUpdatedDTO) {
        Optional<Project> existingProject = projectRepository.findById(projectRequestUpdatedDTO.getId());

        if (existingProject.isEmpty()) {
            return null;
        }

        existingProject.get().setStatus(projectRequestUpdatedDTO.getStatus());
        existingProject.get().setGoal(projectRequestUpdatedDTO.getGoal());
        existingProject.get().setSkill(projectRequestUpdatedDTO.getSkill());
        existingProject.get().setAgeRecomment(projectRequestUpdatedDTO.getAgeRecomment());
        existingProject.get().setIntroVideoUrl(projectRequestUpdatedDTO.getIntroVideoUrl());

        return modelMapper.map(projectRepository.save(existingProject.get()), ProjectResponseDTO.class);
    }

    @Override
    public List<ProjectResponseDTO> getProjects() {
        return projectRepository
                .findAll()
                .stream()
                .map(element -> modelMapper.map(element, ProjectResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectResponseDTO getProjectById(String id) {
        Optional<Project> existingProject = projectRepository.findById(id);

        if (existingProject.isEmpty()) {
            return null;
        }

        return modelMapper.map(existingProject.get(), ProjectResponseDTO.class);
    }
}
