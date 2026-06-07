package taskmanagement.users.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import taskmanagement.users.domain.entity.User;
import taskmanagement.users.domain.repository.UserRepository;
import taskmanagement.users.infrastructure.persistence.entity.UserJpaEntity;
import taskmanagement.users.infrastructure.persistence.mapper.UserPersistenceMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper mapper;
    public UserRepositoryImpl(
            UserJpaRepository userJpaRepository, UserPersistenceMapper mapper) {
        this.userJpaRepository = userJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {

        UserJpaEntity entity = mapper.toJpaEntity(user);

        UserJpaEntity savedEntity =
                userJpaRepository.save(entity);

        return mapper.toDomain(savedEntity);
    }
    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

}
