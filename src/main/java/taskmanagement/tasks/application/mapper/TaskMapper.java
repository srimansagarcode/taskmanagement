package taskmanagement.tasks.application.mapper;

import org.mapstruct.Mapper;
import taskmanagement.tasks.application.dto.TaskRequestDto;
import taskmanagement.tasks.application.dto.TaskResponseDto;
import taskmanagement.tasks.domain.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toDomain(TaskRequestDto dto);

    TaskResponseDto toResponse(Task task);
}
