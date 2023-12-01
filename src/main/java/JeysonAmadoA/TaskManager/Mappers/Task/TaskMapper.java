package JeysonAmadoA.TaskManager.Mappers.Task;

import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Mappers.BaseMap;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper extends BaseMap<TaskDto, TaskEntity> {
    public TaskMapper() {
        super(TaskDto.class, TaskEntity.class);
    }

    public TaskEntity update(TaskEntity taskEntity, TaskDto taskDto){
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(taskDto, taskEntity);
        return taskEntity;
    }
}