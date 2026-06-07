package taskmanagement.tasks.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanagement.tasks.infrastructure.persistence.entity.TaskJpaEntity;

public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, Long> {
}
