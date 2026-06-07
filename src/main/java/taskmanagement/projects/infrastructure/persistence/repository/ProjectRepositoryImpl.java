package taskmanagement.projects.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import taskmanagement.projects.domain.entity.Project;
import taskmanagement.projects.domain.repository.ProjectRepository;
import taskmanagement.projects.infrastructure.persistence.entity.ProjectJpaEntity;
import taskmanagement.projects.infrastructure.persistence.mapper.ProjectPersistenceMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectPersistenceMapper mapper;

    public ProjectRepositoryImpl(
            ProjectJpaRepository projectJpaRepository,
            ProjectPersistenceMapper mapper) {
        this.projectJpaRepository = projectJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Project save(Project project) {
        ProjectJpaEntity entity = mapper.toEntity(project);

        ProjectJpaEntity saveEntity = projectJpaRepository.save(entity);

        return mapper.toDomain(saveEntity);
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Project> findAll() {
        return projectJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

}
