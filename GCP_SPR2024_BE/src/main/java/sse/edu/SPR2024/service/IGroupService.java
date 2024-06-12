package sse.edu.SPR2024.service;

import sse.edu.SPR2024.dto.request.CourseValidRequestDTO;
import sse.edu.SPR2024.dto.request.GroupRequestDTO;
import sse.edu.SPR2024.dto.response.GroupFullResponseDTO;
import sse.edu.SPR2024.dto.response.GroupResponseDTO;

import java.util.List;

public interface IGroupService {

    GroupResponseDTO create(GroupRequestDTO groupRequestDTO);

    GroupResponseDTO update(GroupRequestDTO groupRequestDTO);
    List<GroupResponseDTO> getListGroupByUserId(String userId);
    Boolean checkEnrollmentValid(CourseValidRequestDTO courseValidRequestDTO);
    GroupResponseDTO importGroup(GroupRequestDTO groupRequestDTO);

    GroupResponseDTO getGroupById(String id);
    GroupFullResponseDTO getGroupFullInfoById(String id);

    void deleteGroup(String id);

}
