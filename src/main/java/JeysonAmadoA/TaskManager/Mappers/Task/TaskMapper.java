package JeysonAmadoA.TaskManager.Mappers.Task;

import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Mappers.BaseMap;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper extends BaseMap<TaskDto, TaskEntity> {
    private final TaskTypeMapper taskTypeMapper;
    private final TaskStatusMapper taskStatusMapper;

    public TaskMapper(TaskTypeMapper taskTypeMapper, TaskStatusMapper taskStatusMapper) {
        super(TaskDto.class, TaskEntity.class);
        this.taskTypeMapper = taskTypeMapper;
        this.taskStatusMapper = taskStatusMapper;
    }

    @Override
    public TaskDto toDto(TaskEntity entity) {
        TaskDto dto = super.toDto(entity);
        dto.setTaskType(taskTypeMapper.toDto(entity.getTaskType()));
        dto.setTaskStatus(taskStatusMapper.toDto(entity.getTaskStatus()));
        return dto;
    }

    @Override
    public TaskEntity toEntity(TaskDto dto) {
        TaskEntity entity = super.toEntity(dto);
        entity.setTaskType(taskTypeMapper.toEntity(dto.getTaskType()));
        entity.setTaskStatus(taskStatusMapper.toEntity(dto.getTaskStatus()));
        return entity;
    }



}