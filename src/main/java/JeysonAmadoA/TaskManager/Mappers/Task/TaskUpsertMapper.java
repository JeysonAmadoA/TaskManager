package JeysonAmadoA.TaskManager.Mappers.Task;

import JeysonAmadoA.TaskManager.Dto.Task.TaskUpsertDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Mappers.BaseMap;
import org.springframework.stereotype.Component;

@Component
public class TaskUpsertMapper extends BaseMap<TaskUpsertDto, TaskEntity> {

    public TaskUpsertMapper() {
        super(TaskUpsertDto.class, TaskEntity.class);
    }


    public TaskEntity update(TaskEntity taskEntity, TaskUpsertDto taskUpdateDto) {
        this.modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(taskUpdateDto, taskEntity);
        return taskEntity;
    }
}

