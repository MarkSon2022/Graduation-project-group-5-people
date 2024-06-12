package sse.edu.SPR2024.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String userId;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String gender;
    private Integer age;
    private Date birthDate;
}
