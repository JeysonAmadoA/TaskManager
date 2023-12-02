package JeysonAmadoA.TaskManager.Unit.Dto;

import JeysonAmadoA.TaskManager.Dto.Task.TaskUpsertDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskUpsertDtoTest {

    @Test
    public void settersAndGettersTest(){

        TaskUpsertDto upsertDto = new TaskUpsertDto();
        upsertDto.setTaskName("Task name");
        upsertDto.setTaskTypeId(2L);
        upsertDto.setTaskStatusId(1L);
        upsertDto.setDescription("Description");

        assertEquals("Task name", upsertDto.getTaskName());
        assertEquals(2L, upsertDto.getTaskTypeId());
        assertEquals(1L, upsertDto.getTaskStatusId());
        assertEquals("Description", upsertDto.getDescription());
    }
}
