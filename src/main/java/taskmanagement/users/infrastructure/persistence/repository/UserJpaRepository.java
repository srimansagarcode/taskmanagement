package taskmanagement.users.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanagement.users.infrastructure.persistence.entity.UserJpaEntity;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    Optional<UserJpaEntity> findByEmail(String email);
}
