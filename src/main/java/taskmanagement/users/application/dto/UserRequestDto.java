package taskmanagement.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long roleId;

//    private String userEmail;

}
