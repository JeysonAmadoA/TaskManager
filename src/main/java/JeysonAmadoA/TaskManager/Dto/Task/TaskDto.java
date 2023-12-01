package JeysonAmadoA.TaskManager.Dto.Task;

import lombok.Data;

@Data
public class TaskDto {

    private String taskName;

    private Long taskTypeId;

    private Long taskStatusId;

    private String description;
}
