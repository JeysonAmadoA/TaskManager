package JeysonAmadoA.TaskManager.Unit.Dto;

import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Dto.Task.TaskStatusDto;
import JeysonAmadoA.TaskManager.Dto.Task.TaskTypeDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskDtoTest {

    @Test
    public void settersAndGettersTest(){

        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setTaskName("Task name");
        taskDto.setTaskType(new TaskTypeDto());
        taskDto.setTaskStatus(new TaskStatusDto());
        taskDto.setDescription("Description");

        assertEquals(1L, taskDto.getId());
        assertEquals("Task name", taskDto.getTaskName());
        assertNotNull(taskDto.getTaskType());
        assertNotNull(taskDto.getTaskStatus());
        assertEquals("Description", taskDto.getDescription());
    }
}
