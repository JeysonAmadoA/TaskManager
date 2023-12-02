package JeysonAmadoA.TaskManager.Mappers.Task;

import JeysonAmadoA.TaskManager.Dto.Task.TaskStatusDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskStatusEntity;
import JeysonAmadoA.TaskManager.Mappers.BaseMap;
import org.springframework.stereotype.Component;

@Component
public class TaskStatusMapper extends BaseMap<TaskStatusDto, TaskStatusEntity> {
    public TaskStatusMapper() {
        super(TaskStatusDto.class, TaskStatusEntity.class);
    }
}
