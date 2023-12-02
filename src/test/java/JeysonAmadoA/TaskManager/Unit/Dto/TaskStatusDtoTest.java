package JeysonAmadoA.TaskManager.Unit.Dto;

import JeysonAmadoA.TaskManager.Dto.Task.TaskStatusDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TaskStatusDtoTest {

    @Test
    public void settersAndGettersTest(){

        TaskStatusDto statusDto = new TaskStatusDto();
        statusDto.setName("StatusName");
        statusDto.setFinished(true);

        assertEquals("StatusName", statusDto.getName());
        assertTrue(statusDto.isFinished());
    }
}
