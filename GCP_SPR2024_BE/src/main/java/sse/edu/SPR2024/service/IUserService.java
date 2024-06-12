package sse.edu.SPR2024.service;

import com.nimbusds.jose.JOSEException;
import sse.edu.SPR2024.dto.request.EmployeeRequestDTO;
import sse.edu.SPR2024.dto.request.SignInUserRequestDTO;
import sse.edu.SPR2024.dto.request.UpdateRoleDTO;
import sse.edu.SPR2024.dto.response.UserResponseDTO;
import sse.edu.SPR2024.entity.User;

import java.util.List;

public interface IUserService {
    UserResponseDTO getUserByEmail(String email);

    UserResponseDTO updateUserRole(UpdateRoleDTO updateRoleDTO);

    List<UserResponseDTO> getAllUsers();
    UserResponseDTO signIn(SignInUserRequestDTO signInUserRequestDTO) throws JOSEException;
    List<UserResponseDTO> getAllEmployee();
    List<UserResponseDTO> importEmployee(List<EmployeeRequestDTO> employeeRequestDTOS);
}
