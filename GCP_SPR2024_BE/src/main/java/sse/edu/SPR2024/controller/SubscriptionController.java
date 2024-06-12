package sse.edu.SPR2024.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.consts.RestPath;
import sse.edu.SPR2024.dto.request.SubscriptionRequestDTO;
import sse.edu.SPR2024.dto.response.SubscriptionResponseDTO;
import sse.edu.SPR2024.service.ISubscriptionService;

import java.util.List;

@RestController
@RequestMapping(RestPath.SUBSCRIPTION)
@RequiredArgsConstructor
public class SubscriptionController {
    @Autowired
    ISubscriptionService subscriptionService;

    @PostMapping()
    public ResponseEntity<SubscriptionResponseDTO> createOrUpdateSubscription(@RequestBody SubscriptionRequestDTO subscriptionRequestDTO,
                                                                              @RequestHeader(value = "accessToken", required = false) String token,
                                                                              HttpServletRequest request) {
        SubscriptionResponseDTO response;

        if (subscriptionRequestDTO.getId() == null) {
            response = subscriptionService.create(subscriptionRequestDTO, request);
        } else {
            response = subscriptionService.update(subscriptionRequestDTO);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponseDTO> getSubscriptionById(@PathVariable String id) {
        SubscriptionResponseDTO response = subscriptionService.getSubcriptionById(id);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<SubscriptionResponseDTO>> getSubscriptions() {
        List<SubscriptionResponseDTO> response = subscriptionService.getSubscriptions();
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pay-status")
    public ResponseEntity<SubscriptionResponseDTO> updateStatus(HttpServletRequest request) {
        SubscriptionResponseDTO response = subscriptionService.updateStatus(request);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<SubscriptionResponseDTO>> getSubcriptionByMentorId(@PathVariable String mentorId) {
        List<SubscriptionResponseDTO> response = subscriptionService.getActiveSubcriptionByMentorId(mentorId);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/mentor/{mentorId}/all")
    public ResponseEntity<List<SubscriptionResponseDTO>> getAllSubcriptionByMentorId(@PathVariable String mentorId) {
        List<SubscriptionResponseDTO> response = subscriptionService.getAllSubcriptionByOrgId(mentorId);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
