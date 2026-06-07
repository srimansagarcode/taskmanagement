package taskmanagement.tasks.infrastructure.persistence.repository;

import org.springframework.stereotype.Repository;
import taskmanagement.tasks.domain.entity.Task;
import taskmanagement.tasks.domain.repository.TaskRepository;
import taskmanagement.tasks.infrastructure.persistence.entity.TaskJpaEntity;
import taskmanagement.tasks.infrastructure.persistence.mapper.TaskPersistenceMapper;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private final TaskJpaRepository taskJpaRepository;
    private final TaskPersistenceMapper mapper;

    public TaskRepositoryImpl(
            TaskJpaRepository taskJpaRepository,
            TaskPersistenceMapper mapper
    ) {
        this.taskJpaRepository = taskJpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Task save(Task task) {
        TaskJpaEntity entity = mapper.toEntity(task);

        TaskJpaEntity saveEntity = taskJpaRepository.save(entity);

        return mapper.toDomain(saveEntity);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Task> findAll() {
        return taskJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

}
