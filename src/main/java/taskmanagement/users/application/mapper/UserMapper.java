package taskmanagement.users.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import taskmanagement.users.application.dto.UserRequestDto;
import taskmanagement.users.application.dto.UserResponseDto;
import taskmanagement.users.domain.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(source = "userEmail", target = "email");
    User toDomain(UserRequestDto dto);

    UserResponseDto toResponse(User user);
}
