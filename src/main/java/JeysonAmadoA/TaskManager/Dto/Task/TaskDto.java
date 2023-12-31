package JeysonAmadoA.TaskManager.Dto.Task;

import lombok.Data;

@Data
public class TaskDto {

    private Long id;

    private String taskName;

    private TaskTypeDto taskType;

    private TaskStatusDto taskStatus;

    private String description;
}
