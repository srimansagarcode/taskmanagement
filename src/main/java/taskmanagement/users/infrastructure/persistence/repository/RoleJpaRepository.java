package taskmanagement.users.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanagement.users.infrastructure.persistence.entity.RoleJpaEntity;

public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity, Long> {
}
