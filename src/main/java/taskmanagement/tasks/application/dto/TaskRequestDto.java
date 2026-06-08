package taskmanagement.tasks.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskRequestDto {
    private String title;

    private String description;

    private Long projectId;
}
