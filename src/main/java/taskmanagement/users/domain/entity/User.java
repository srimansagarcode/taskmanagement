package taskmanagement.users.domain.entity;

import taskmanagement.users.domain.enums.UserStatus;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserStatus status;
}
