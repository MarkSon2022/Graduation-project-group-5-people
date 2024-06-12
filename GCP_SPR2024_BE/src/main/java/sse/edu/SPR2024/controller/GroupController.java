package sse.edu.SPR2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.consts.RestPath;
import sse.edu.SPR2024.dto.request.CourseValidRequestDTO;
import sse.edu.SPR2024.dto.request.GroupRequestDTO;
import sse.edu.SPR2024.dto.response.GroupFullResponseDTO;
import sse.edu.SPR2024.dto.response.GroupResponseDTO;
import sse.edu.SPR2024.service.IGroupService;

import java.util.List;

@RestController
@RequestMapping(RestPath.GROUP)
@RequiredArgsConstructor
public class GroupController {
    @Autowired
    IGroupService groupService;

    @PostMapping()
    public ResponseEntity<GroupResponseDTO> createOrUpdateGroup(@RequestBody GroupRequestDTO groupRequestDTO, @RequestHeader(value = "accessToken", required = false) String token) {
        GroupResponseDTO response;

        if (groupRequestDTO.getId() == null) {
            response = groupService.create(groupRequestDTO);
        } else {
            response = groupService.update(groupRequestDTO);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupResponseDTO> getGroupById(@PathVariable String id) {
        GroupResponseDTO group = groupService.getGroupById(id);
        if (group != null) {
            return new ResponseEntity<>(group, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable String id) {
        groupService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroupResponseDTO>> getListGroupByUserId(@PathVariable String userId) {
        List<GroupResponseDTO> groups = groupService.getListGroupByUserId(userId);
        if (groups != null) {
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/enroll")
    public ResponseEntity<Boolean> getEnrollmentValid(@RequestBody CourseValidRequestDTO courseValidRequestDTO) {
        Boolean isValid = groupService.checkEnrollmentValid(courseValidRequestDTO);
        if (isValid) {
            return new ResponseEntity<>(isValid, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/import")
    public ResponseEntity<GroupResponseDTO> importGroup(@RequestBody GroupRequestDTO groupRequestDTOs) {
        GroupResponseDTO groupResponseDTO = groupService.importGroup(groupRequestDTOs);
        return new ResponseEntity<>(groupResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<GroupFullResponseDTO> getGroupFullInfoById(@PathVariable String id) {
        GroupFullResponseDTO group = groupService.getGroupFullInfoById(id);
        if (group != null) {
            return new ResponseEntity<>(group, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
