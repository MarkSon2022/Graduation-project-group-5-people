package sse.edu.SPR2024.controller;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import sse.edu.SPR2024.dto.request.EmployeeRequestDTO;
import sse.edu.SPR2024.dto.request.SignInUserRequestDTO;
import sse.edu.SPR2024.dto.request.UpdateRoleDTO;
import sse.edu.SPR2024.dto.response.AuthResponseDTO;
import sse.edu.SPR2024.dto.response.CourseResponseDTO;
import sse.edu.SPR2024.dto.response.UserResponseDTO;
import sse.edu.SPR2024.security.GoogleTokenInfo;
import sse.edu.SPR2024.security.JWTGenerator;
import sse.edu.SPR2024.service.IUserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator;

    private static final String GOOGLE_API_URL = "https://www.googleapis.com/oauth2/v3/userinfo?access_token=";
//
//    @PostMapping("/account_create")
//    public ResponseEntity<String> CreateAccount() {
//        Account account = new Account("ngonc", "ngonc@gmail.com", Bcrypt.hashPassword("ngonc"), "NgoNC", null, null, 22, null, true, null,Role.ROLE_ADMIN, null, null,null);
//        //Account account = new Account();
//        userService.CreateAccount(account);
//        return new ResponseEntity<>("Account Create Successfully", HttpStatus.OK);
//    }
//
//    @GetMapping("/auth/getAccount")
//    public ResponseEntity<Account> GetAccount() {
//        Account account = userService.GetAccountByEmail("ngonc@gmail.com");
//        return new ResponseEntity<>(account, HttpStatus.OK);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDTO.getEmail(), loginDTO.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerator.generateToken(authentication);
//        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
//    }
//
    @PostMapping("/loginByGoogle")
    public ResponseEntity<AuthResponseDTO> loginByGoogle(@RequestBody String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = GOOGLE_API_URL + accessToken;
        GoogleTokenInfo tokenInfo = restTemplate.getForObject(url, GoogleTokenInfo.class);
        if (Objects.nonNull(tokenInfo.getError()) && !tokenInfo.getError().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        UserResponseDTO user = userService.getUserByEmail(tokenInfo.getEmail());
        if (Objects.nonNull(user)) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(), null));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
//
//    @PostMapping(value = {"learners/signup", "learners/register"})
//    public ResponseEntity<String> registerLearner(@RequestBody RegisterDTO registerDTO) {
//        String response = userService.registerLearner(registerDTO);
//        return new ResponseEntity<String>(response, HttpStatus.CREATED);
//    }
//
//
//    @PostMapping(value = {"mentors/signup", "mentors/register"})
//    public ResponseEntity<String> registerMentor(@RequestBody RegisterDTO registerDTO) {
//        String response = userService.registerMentor(registerDTO);
//        return new ResponseEntity<String>(response, HttpStatus.CREATED);
//    }

    @PostMapping("/updateRole")
    public ResponseEntity<UserResponseDTO> updateRoleByEmail(@RequestBody UpdateRoleDTO updateRoleDTO) {
        UserResponseDTO response = userService.updateUserRole(updateRoleDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserResponseDTO> signIn(@RequestBody SignInUserRequestDTO signInUserRequestDTO) throws JOSEException {
        UserResponseDTO user = userService.signIn(signInUserRequestDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<UserResponseDTO>> getAllEmployee() {
        List<UserResponseDTO> users = userService.getAllEmployee();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/employee/import")
    public ResponseEntity<List<UserResponseDTO>> importEmployee(@RequestBody List<EmployeeRequestDTO> employeeRequestDTOS) {
        List<UserResponseDTO> users = userService.importEmployee(employeeRequestDTOS);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
