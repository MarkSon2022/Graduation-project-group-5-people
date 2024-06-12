package sse.edu.SPR2024.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.poi.util.StringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.OrganizationRequestDTO;
import sse.edu.SPR2024.dto.response.OrganizationResponseDTO;
import sse.edu.SPR2024.dto.response.SubscriptionPackageCourseInfoResponse;
import sse.edu.SPR2024.dto.response.SubscriptionResponseDTO;
import sse.edu.SPR2024.dto.response.UserResponseDTO;
import sse.edu.SPR2024.entity.Organization;
import sse.edu.SPR2024.entity.User;
import sse.edu.SPR2024.enums.UserRole;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.IOrganizationRepository;
import sse.edu.SPR2024.repository.ISubscriptionRepository;
import sse.edu.SPR2024.repository.IUserRepository;
import sse.edu.SPR2024.service.IOrganizationService;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static sse.edu.SPR2024.utils.StringUtils.randomPassword;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {

    private final IOrganizationRepository organizationRepository;
    private final ISubscriptionRepository subscriptionRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    /**
     * @param organizationRequestDTO
     * @return
     */
    @Override
    public OrganizationResponseDTO create(OrganizationRequestDTO organizationRequestDTO) {
        Organization organizationSave = modelMapper.map(organizationRequestDTO, Organization.class);
        organizationSave.setCreatedDate(OffsetDateTime.now());
        return modelMapper.map(organizationRepository.save(organizationSave), OrganizationResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> importData(List<OrganizationRequestDTO> organizationRequestDTOs) {
        List<Organization> organizations = organizationRequestDTOs.stream()
                .map(organizationRequestDTO -> modelMapper.map(organizationRequestDTO, Organization.class))
                .collect(Collectors.toList());

        List<User> users = new ArrayList<>();

        organizations.forEach(organization -> {
            String id = UUID.randomUUID().toString();
            organization.setOrgId(id);
            organization.setCreatedDate(OffsetDateTime.now());

            Organization organizationCheck = organizationRepository
                    .findFirstByUserEmail(organization.getUser().getEmail());

            if (organizationCheck != null) {
                return;
            }

            User user = User
                    .builder()
                    .userId(id)
                    .status(true)
                    .email(organization.getUser().getEmail())
                    .fullName(organization.getOrgName())
                    .password(randomPassword(8))
                    .role(UserRole.ORGANIZATION.name())
                    .organization(organization)
                .build();
            organization.setUser(user);
            user.setOrganization(organization);
            users.add(user);
        });


        return userRepository.saveAll(users).stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * @param organizationRequestDTO
     * @return
     */
    @Override
    public OrganizationResponseDTO update(OrganizationRequestDTO organizationRequestDTO) {
        Organization existingOrganization = organizationRepository.findById(organizationRequestDTO.getId())
                .orElseThrow(() -> new ServiceDataException(MessageFormat.format("Organization not found with id: {0}", organizationRequestDTO.getId())));
        existingOrganization.setOrgName(organizationRequestDTO.getOrgName());
        existingOrganization.setOrgLogoUrl(organizationRequestDTO.getOrgLogoUrl());

        existingOrganization.setModifiedDate(OffsetDateTime.now());
        return modelMapper.map(organizationRepository.save(existingOrganization), OrganizationResponseDTO.class);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public OrganizationResponseDTO getOrganizationById(String id) {
        Optional<Organization> organizationOptional = organizationRepository.findById(id);

        if (organizationOptional.isEmpty()) {
            throw new ServiceDataException("Can not find organization");
        }

        return modelMapper.map(organizationOptional.get(), OrganizationResponseDTO.class);
    }

    /**
     * @param orgId
     * @return
     */
    @Override
    public List<SubscriptionPackageCourseInfoResponse> getSubscriptionListByOrgId(String orgId) {
        boolean isExistOrganization = organizationRepository.existsById(orgId);
        if (!isExistOrganization) {
            throw new ServiceDataException(MessageFormat.format("Organization not found with id: {0}", orgId));
        }
        //List<SubscriptionPackageCourseInfoResponse> collect = subscriptionRepository.findByOrgId(orgId);
        return null;
    }

    @Override
    public List<OrganizationResponseDTO> getAllOrganizations() {
        return organizationRepository.findAll().stream()
                .map(organization -> modelMapper.map(organization, OrganizationResponseDTO.class))
                .collect(Collectors.toList());
    }
}
