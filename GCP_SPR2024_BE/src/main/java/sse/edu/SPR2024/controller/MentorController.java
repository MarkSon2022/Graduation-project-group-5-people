package sse.edu.SPR2024.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sse.edu.SPR2024.consts.RestPath;
import sse.edu.SPR2024.dto.request.MentorRequestDTO;
import sse.edu.SPR2024.dto.response.MentorResponseDTO;
import sse.edu.SPR2024.dto.response.UserResponseDTO;
import sse.edu.SPR2024.service.IMentorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mentors")
@RequiredArgsConstructor
public class MentorController {
    private final IMentorService mentorService;

    @GetMapping("/{id}")
    public ResponseEntity<MentorResponseDTO> getMentorById(@PathVariable String id) {
        MentorResponseDTO mentor = mentorService.getById(id);
        if (mentor != null) {
            return new ResponseEntity<>(mentor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/organization/{orgId}")
    public ResponseEntity<List<MentorResponseDTO>> getMentorsByOrganization(@PathVariable String orgId) {
        List<MentorResponseDTO> mentors = mentorService.getByOrganization(orgId);
        if (mentors != null) {
            return new ResponseEntity<>(mentors, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/import")
    public ResponseEntity<List<UserResponseDTO>> importMentors(@RequestBody List<MentorRequestDTO> mentorRequestDTOList) {
        List<UserResponseDTO> response = mentorService.importMentors(mentorRequestDTOList);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
