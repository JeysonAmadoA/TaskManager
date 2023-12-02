package JeysonAmadoA.TaskManager.Integration.Mappers;

import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Dto.Task.TaskStatusDto;
import JeysonAmadoA.TaskManager.Dto.Task.TaskTypeDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Entities.Task.TaskStatusEntity;
import JeysonAmadoA.TaskManager.Entities.Task.TaskTypeEntity;
import JeysonAmadoA.TaskManager.Mappers.Task.TaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TaskMapperTest {

    private final TaskMapper taskMapper;

    @Autowired
    public TaskMapperTest(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Test
    public void TaskDtoToEntityTest(){

        TaskStatusDto statusDto = new TaskStatusDto();
        statusDto.setName("Status");
        statusDto.setFinished(true);

        TaskTypeDto typeDto = new TaskTypeDto();
        typeDto.setName("Type");

        TaskDto taskDto = new TaskDto();
        taskDto.setTaskName("typeName");
        taskDto.setTaskStatus(statusDto);
        taskDto.setTaskType(typeDto);
        taskDto.setDescription("description");

        TaskEntity taskEntity = this.taskMapper.toEntity(taskDto);
        assertEquals("typeName", taskEntity.getTaskName());
        assertEquals("Status", taskEntity.getTaskStatus().getName());
        assertTrue(taskEntity.getTaskStatus().isFinished());
        assertEquals("Type", taskEntity.getTaskType().getName());
        assertEquals("description", taskEntity.getDescription());
    }

    @Test
    public void TaskEntityToDtoTest(){

        TaskStatusEntity statusEntity = new TaskStatusEntity();
        statusEntity.setName("Status");
        statusEntity.setFinished(true);

        TaskTypeEntity typeEntity = new TaskTypeEntity();
        typeEntity.setName("Type");

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName("typeName");
        taskEntity.setTaskStatus(statusEntity);
        taskEntity.setTaskType(typeEntity);
        taskEntity.setDescription("description");

        TaskDto taskDto = this.taskMapper.toDto(taskEntity);
        assertEquals("typeName", taskDto.getTaskName());
        assertEquals("Status", taskDto.getTaskStatus().getName());
        assertTrue(taskDto.getTaskStatus().isFinished());
        assertEquals("Type", taskDto.getTaskType().getName());
        assertEquals("description", taskDto.getDescription());
    }
}
