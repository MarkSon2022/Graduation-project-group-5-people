package sse.edu.SPR2024.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModeratorDTO {
    private String userId;
    private String email;
    private String password;
    private String fullName;
    private String address;
    private String gender;
    private Integer age;
    private Date birthDate;
    private String imgUrl;
   // private String organizationName;
  //  @NonNull
   // private String organizationId;
    @NonNull
    private String moderatorId;
}
