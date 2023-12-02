package JeysonAmadoA.TaskManager.Integration.Mappers;

import JeysonAmadoA.TaskManager.Dto.Task.TaskStatusDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskStatusEntity;
import JeysonAmadoA.TaskManager.Mappers.Task.TaskStatusMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TaskStatusMapperTest {

    private final TaskStatusMapper statusMapper;

    @Autowired
    public TaskStatusMapperTest(TaskStatusMapper statusMapper) {
        this.statusMapper = statusMapper;
    }

    @Test
    public void TaskStatusDtoToEntityTest(){

        TaskStatusDto statusDto = new TaskStatusDto();
        statusDto.setName("typeName");
        statusDto.setFinished(true);

        TaskStatusEntity taskStatusEntity = this.statusMapper.toEntity(statusDto);
        assertEquals("typeName", taskStatusEntity.getName());
        assertTrue(taskStatusEntity.isFinished());
    }

    @Test
    public void TaskStatusEntityToDtoTest(){

        TaskStatusEntity taskStatusEntity = new TaskStatusEntity();
        taskStatusEntity.setName("typeName");
        taskStatusEntity.setFinished(true);

        TaskStatusDto statusDto = this.statusMapper.toDto(taskStatusEntity);
        assertEquals("typeName", statusDto.getName());
        assertTrue(statusDto.isFinished());
    }
}
