package sse.edu.SPR2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.consts.RestPath;
import sse.edu.SPR2024.dto.request.OrganizationRequestDTO;
import sse.edu.SPR2024.dto.response.OrganizationResponseDTO;
import sse.edu.SPR2024.dto.response.SubscriptionPackageCourseInfoResponse;
import sse.edu.SPR2024.dto.response.UserResponseDTO;
import sse.edu.SPR2024.service.IOrganizationService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(RestPath.ORGANIZATION)
@RequiredArgsConstructor
public class OrganizationController {
    @Autowired
    IOrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationResponseDTO> createOrUpdateOrganization(@RequestBody OrganizationRequestDTO organizationRequestDTO,
                                                                              @RequestHeader(value = "accessToken", required = false) String token) {
        OrganizationResponseDTO response;

        if (organizationRequestDTO.getId() == null) {
            response = organizationService.create(organizationRequestDTO);
        } else {
            response = organizationService.update(organizationRequestDTO);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponseDTO> getOrganizationById(@PathVariable String id) {
        OrganizationResponseDTO organization = organizationService.getOrganizationById(id);
        if (organization != null) {
            return new ResponseEntity<>(organization, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("subcriptionList")
    public ResponseEntity<List<SubscriptionPackageCourseInfoResponse>> getSubcriptionListByOrgId(@RequestParam(required = false) String orgId) {
        List<SubscriptionPackageCourseInfoResponse> response = organizationService.getSubscriptionListByOrgId(orgId);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<OrganizationResponseDTO>> getAllOrganizations() {
        List<OrganizationResponseDTO> response = organizationService.getAllOrganizations();
        if (Objects.nonNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/import")
    public ResponseEntity<List<UserResponseDTO>> importData(@RequestBody List<OrganizationRequestDTO> organizationRequestDTOs) {
        List<UserResponseDTO> response = organizationService.importData(organizationRequestDTOs);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
