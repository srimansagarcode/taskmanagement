package taskmanagement.projects.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import taskmanagement.projects.domain.entity.Project;
import taskmanagement.projects.infrastructure.persistence.entity.ProjectJpaEntity;

@Component
public class ProjectPersistenceMapper {
    public ProjectJpaEntity toEntity(Project project) {
        ProjectJpaEntity entity = new ProjectJpaEntity();

        entity.setId(project.getId());
        entity.setName(project.getName());
        entity.setDescription(project.getDescription());
        entity.setStatus(project.getStatus());

        return entity;
    }

    public Project toDomain(ProjectJpaEntity entity) {
        Project project = new Project();

        project.setId(entity.getId());
        project.setName(entity.getName());
        project.setDescription(entity.getDescription());
        project.setStatus(entity.getStatus());

        return project;
    }
}
