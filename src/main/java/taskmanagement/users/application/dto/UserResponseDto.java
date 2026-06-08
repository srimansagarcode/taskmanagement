package taskmanagement.users.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taskmanagement.users.domain.enums.UserStatus;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private UserStatus status;

//    private String userEmail;
}
