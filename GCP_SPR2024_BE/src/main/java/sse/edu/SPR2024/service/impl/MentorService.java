package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.MentorRequestDTO;
import sse.edu.SPR2024.dto.response.MentorResponseDTO;
import sse.edu.SPR2024.dto.response.UserResponseDTO;
import sse.edu.SPR2024.entity.Mentor;
import sse.edu.SPR2024.entity.Organization;
import sse.edu.SPR2024.entity.User;
import sse.edu.SPR2024.enums.UserRole;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.IMentorRepository;
import sse.edu.SPR2024.repository.IOrganizationRepository;
import sse.edu.SPR2024.repository.IUserRepository;
import sse.edu.SPR2024.service.IMentorService;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static sse.edu.SPR2024.utils.StringUtils.randomPassword;

@Service
@RequiredArgsConstructor
public class MentorService implements IMentorService {
    private final IMentorRepository mentorRepository;
    private final IOrganizationRepository organizationRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public MentorResponseDTO getById(String id) {
        Mentor mentor = mentorRepository
                .findById(id)
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Error when getting mentor with id {0}", id)));

        return modelMapper.map(mentor, MentorResponseDTO.class);
    }

    @Override
    public List<MentorResponseDTO> getByOrganization(String orgId) {
        List<Mentor> mentor = mentorRepository.findAllByOrganizationOrgId(orgId);

        return mentor.stream().map(ele -> {
            return modelMapper.map(ele, MentorResponseDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> importMentors(List<MentorRequestDTO> mentorRequestDTOList) {
        List<User> users = new ArrayList<>();

        mentorRequestDTOList.forEach(mentor -> {
            String id = UUID.randomUUID().toString();

            Mentor mentorCheck = mentorRepository
                    .findFirstByEmail(mentor.getEmail());

            if (mentorCheck != null) {
                return;
            }

            Mentor mentorSave = new Mentor();

            Organization organization = organizationRepository
                    .findById(mentor.getOrgId())
                    .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Error when getting organization with id {0}", mentor.getOrgId())));

            mentorSave.setId(id);
            mentorSave.setOrganization(organization);
            mentorSave.setName(mentor.getName());
            mentorSave.setEmail(mentor.getEmail());
            mentorSave.setImgUrl(mentor.getImgUrl());
            User user = User
                    .builder()
                    .userId(id)
                    .status(true)
                    .email(mentor.getEmail())
                    .fullName(mentor.getName())
                    .password(randomPassword(8))
                    .role(UserRole.MENTOR.name())
                    .mentor(mentorSave)
                    .build();
            mentorSave.setUser(user);
            mentorSave.setOrganization(organization);
            user.setMentor(mentorSave);
            users.add(user);
        });

        return userRepository.saveAll(users).stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MentorResponseDTO> getBySubscription(String subscriptionId) {
        List<Mentor> mentor = new ArrayList<>();
        return mentor.stream().map(ele -> {
            return modelMapper.map(ele, MentorResponseDTO.class);
        }).collect(Collectors.toList());
    }

    @Override
    public MentorResponseDTO create(MentorRequestDTO mentorRequestDTO) {
        Mentor mentor = modelMapper.map(mentorRequestDTO, Mentor.class);

        return modelMapper.map(mentorRepository.save(mentor), MentorResponseDTO.class);
    }

    @Override
    public MentorResponseDTO update(MentorRequestDTO mentorRequestDTO) {
        Mentor mentor = modelMapper.map(mentorRequestDTO, Mentor.class);

        return modelMapper.map(mentorRepository.save(mentor), MentorResponseDTO.class);
    }


}
