package JeysonAmadoA.TaskManager.Unit.Dto;

import JeysonAmadoA.TaskManager.Dto.Task.TaskTypeDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTypeDtoTest {

    @Test
    public void settersAndGettersTest(){

        TaskTypeDto typeDto = new TaskTypeDto();
        typeDto.setName("typeName");

        assertEquals("typeName", typeDto.getName());
    }
}
