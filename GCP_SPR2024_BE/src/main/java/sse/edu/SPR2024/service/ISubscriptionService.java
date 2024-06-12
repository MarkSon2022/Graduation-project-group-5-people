package sse.edu.SPR2024.service;

import jakarta.servlet.http.HttpServletRequest;
import sse.edu.SPR2024.dto.request.SubscriptionRequestDTO;
import sse.edu.SPR2024.dto.response.SubscriptionResponseDTO;

import java.util.List;

public interface ISubscriptionService {
    SubscriptionResponseDTO create(SubscriptionRequestDTO subscriptionRequestDTO, HttpServletRequest request);

    SubscriptionResponseDTO update(SubscriptionRequestDTO subscriptionRequestDTO);
    List<SubscriptionResponseDTO> getSubscriptions();
    SubscriptionResponseDTO updateStatus(HttpServletRequest request);

    SubscriptionResponseDTO getSubcriptionById(String id);
    List<SubscriptionResponseDTO> getActiveSubcriptionByMentorId(String userId);
    List<SubscriptionResponseDTO> getAllSubcriptionByOrgId(String orgId);
}
