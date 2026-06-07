package taskmanagement.tasks.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;
import taskmanagement.tasks.domain.entity.Task;
import taskmanagement.tasks.infrastructure.persistence.entity.TaskJpaEntity;

@Component
public class TaskPersistenceMapper {

    public TaskJpaEntity toEntity(Task task) {
        TaskJpaEntity entity = new TaskJpaEntity();

        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setStatus(task.getStatus());
        entity.setPriority(task.getPriority());

        return entity;
    }

    public Task toDomain(TaskJpaEntity entity) {

        Task task = new Task();

        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setStatus(entity.getStatus());
        task.setPriority(entity.getPriority());

        return task;

    }
}
