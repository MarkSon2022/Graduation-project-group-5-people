package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.OrganizationRequestDTO;
import sse.edu.SPR2024.dto.response.OrganizationResponseDTO;
import sse.edu.SPR2024.dto.response.SubscriptionPackageCourseInfoResponse;
import sse.edu.SPR2024.dto.response.UserResponseDTO;

import java.util.List;

public interface IOrganizationService {
    OrganizationResponseDTO create(OrganizationRequestDTO organizationRequestDTO);
    List<UserResponseDTO> importData(List<OrganizationRequestDTO> organizationRequestDTOs);
    OrganizationResponseDTO update(OrganizationRequestDTO organizationRequestDTO);
    OrganizationResponseDTO getOrganizationById(String id);
    List<SubscriptionPackageCourseInfoResponse> getSubscriptionListByOrgId(String orgId);
    List<OrganizationResponseDTO> getAllOrganizations();
}
