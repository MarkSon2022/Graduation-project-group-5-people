package sse.edu.SPR2024.service;

import org.springframework.data.jpa.repository.JpaRepository;
import sse.edu.SPR2024.dto.request.MentorRequestDTO;
import sse.edu.SPR2024.dto.response.MentorResponseDTO;
import sse.edu.SPR2024.dto.response.UserResponseDTO;
import sse.edu.SPR2024.entity.Mentor;

import java.util.List;

public interface IMentorService {
    MentorResponseDTO getById(String id);
    List<MentorResponseDTO> getByOrganization(String orgId);
    List<UserResponseDTO> importMentors(List<MentorRequestDTO> mentorRequestDTOList);
    List<MentorResponseDTO> getBySubscription(String subscriptionId);
    MentorResponseDTO create(MentorRequestDTO mentorRequestDTO);
    MentorResponseDTO update(MentorRequestDTO mentorRequestDTO);
}
