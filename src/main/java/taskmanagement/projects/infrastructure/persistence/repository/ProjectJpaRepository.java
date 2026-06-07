package taskmanagement.projects.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import taskmanagement.projects.infrastructure.persistence.entity.ProjectJpaEntity;

public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, Long> {
}
