package taskmanagement.users.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import taskmanagement.users.domain.entity.User;
import taskmanagement.users.infrastructure.persistence.entity.UserJpaEntity;

@Component
public class UserPersistenceMapper {
    public UserJpaEntity toJpaEntity(User user) {

        UserJpaEntity entity = new UserJpaEntity();

        entity.setId(user.getId());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setStatus(user.getStatus());

        return entity;
    }

    public User toDomain(UserJpaEntity entity) {

        User user = new User();

        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setStatus(entity.getStatus());

        return user;
    }
}
