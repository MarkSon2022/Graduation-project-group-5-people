package sse.edu.SPR2024.service.impl;

import com.nimbusds.jose.JOSEException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sse.edu.SPR2024.dto.request.EmployeeRequestDTO;
import sse.edu.SPR2024.dto.request.SignInUserRequestDTO;
import sse.edu.SPR2024.dto.request.UpdateRoleDTO;
import sse.edu.SPR2024.dto.response.CourseResponseDTO;
import sse.edu.SPR2024.dto.response.UserResponseDTO;
import sse.edu.SPR2024.entity.Learner;
import sse.edu.SPR2024.entity.Mentor;
import sse.edu.SPR2024.entity.Organization;
import sse.edu.SPR2024.entity.User;
import sse.edu.SPR2024.enums.UserRole;
import sse.edu.SPR2024.exception.ServiceDataException;
import sse.edu.SPR2024.repository.ILearnerRepository;
import sse.edu.SPR2024.repository.IMentorRepository;
import sse.edu.SPR2024.repository.IOrganizationRepository;
import sse.edu.SPR2024.repository.IUserRepository;
import sse.edu.SPR2024.service.IUserService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static sse.edu.SPR2024.utils.StringUtils.randomPassword;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IOrganizationRepository organizationRepository;
    private final IMentorRepository mentorRepository;
    private final ILearnerRepository learnerRepository;
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;
    private final TokenService tokenService;

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        User user = getUserDBByEmail(email);
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO updateUserRole(UpdateRoleDTO updateRoleDTO) {
        User user = getUserDBByEmail(updateRoleDTO.getEmail());
        user.setRole(updateRoleDTO.getUserRole().name().toLowerCase());
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    private User getUserDBByEmail (String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);

        Predicate userEmailPredicate = criteriaBuilder.equal(userRoot.get("email"), email);
        query.where(userEmailPredicate);

        User user = entityManager.createQuery(query).getSingleResult();
        return user;
    }

    @Override
    public UserResponseDTO signIn(SignInUserRequestDTO signInUserRequestDTO) throws JOSEException {
        User user = userRepository.findFirstByEmail(signInUserRequestDTO.getEmail());

        if (user != null) {
            List<User> users = userRepository.findAllByEmailOrderByCreatedDateDesc(signInUserRequestDTO.getEmail());
            if (users.size() > 1) {
                user = users.get(0);
            }
            if (user.getFullName().trim().isEmpty()) {
                user.setFullName(signInUserRequestDTO.getName());
                userRepository.save(user);
            }
        }


        if (user == null) {
            String id = signInUserRequestDTO.getGoogleId();
            Learner learner = Learner
                    .builder()
                    .id(id)
                    .avatarUrl(signInUserRequestDTO.getImageUrl())
                    .point(0L)
                    .ranking(0)
                    .build();

            User userSave = User
                    .builder()
                    .userId(id)
                    .status(true)
                    .email(signInUserRequestDTO.getEmail())
                    .fullName(signInUserRequestDTO.getName())
                    .password(randomPassword(8))
                    .role(UserRole.LEARNER.name())
                    .learner(learner)
                    .build();

            learner.setUser(userSave);
            userSave.setLearner(learner);
            User userSaved = userRepository.save(userSave);
            UserResponseDTO userResponseDTO = modelMapper.map(userSaved, UserResponseDTO.class);
            userResponseDTO.setToken(tokenService.generateToken(userResponseDTO));

            return userResponseDTO;
        }

        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        userResponseDTO.setToken(tokenService.generateToken(userResponseDTO));
//        if (user.getRole().equals(UserRole.ORGANIZATION.name())) {
//            Organization organization = organizationRepository
//                    .findById(user.getUserId()).orElseThrow(() -> new ServiceDataException("Organization not found"));
//            userResponseDTO.setPhotoURL(organization.getOrgLogoUrl());
//        }
//        else if (user.getRole().equals(UserRole.MENTOR.name())) {
//            Mentor mentor = mentorRepository
//                    .findById(user.getUserId()).orElseThrow(() -> new ServiceDataException("Mentor not found"));
//            userResponseDTO.setPhotoURL(mentor.getImgUrl());
//        }
//        else {
//            Learner learner = learnerRepository
//                    .findById(user.getUserId()).orElseThrow(() -> new ServiceDataException("Learner not found"));
//            userResponseDTO.setPhotoURL(learner.getAvatarUrl());
//        }

        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> getAllEmployee() {
        return userRepository.findAllByRole(UserRole.EMPLOYEE.name())
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> importEmployee(List<EmployeeRequestDTO> employeeRequestDTOS) {

        List<User> users = new ArrayList<>();

        employeeRequestDTOS.forEach(emp -> {
            String id = UUID.randomUUID().toString();

            User userExist = userRepository.findFirstByEmail(emp.getUser().getEmail());

            if (userExist != null) {
                userExist.setRole(UserRole.EMPLOYEE.name());
                users.add(userExist);
            }
            else {
                User user = User
                        .builder()
                        .userId(id)
                        .status(true)
                        .email(emp.getUser().getEmail())
                        .fullName(emp.getFullName())
                        .password(randomPassword(8))
                        .role(UserRole.EMPLOYEE.name())
                        .build();
                users.add(user);
            }
        });

        return userRepository.saveAll(users).stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}
