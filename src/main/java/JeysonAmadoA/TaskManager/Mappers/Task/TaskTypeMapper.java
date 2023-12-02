package JeysonAmadoA.TaskManager.Mappers.Task;

import JeysonAmadoA.TaskManager.Dto.Task.TaskTypeDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskTypeEntity;
import JeysonAmadoA.TaskManager.Mappers.BaseMap;
import org.springframework.stereotype.Component;

@Component
public class TaskTypeMapper extends BaseMap<TaskTypeDto, TaskTypeEntity> {
    public TaskTypeMapper() {
        super(TaskTypeDto.class, TaskTypeEntity.class);
    }
}
