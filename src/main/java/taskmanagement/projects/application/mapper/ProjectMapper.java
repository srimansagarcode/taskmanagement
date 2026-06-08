package taskmanagement.projects.application.mapper;

import org.mapstruct.Mapper;
import taskmanagement.projects.application.dto.ProjectRequestDto;
import taskmanagement.projects.application.dto.ProjectResponseDto;
import taskmanagement.projects.domain.entity.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toDomain(ProjectRequestDto dto);

    ProjectResponseDto toResponse(Project project);
}
