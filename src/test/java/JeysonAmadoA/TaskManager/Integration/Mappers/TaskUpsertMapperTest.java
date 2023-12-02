package JeysonAmadoA.TaskManager.Integration.Mappers;

import JeysonAmadoA.TaskManager.Dto.Task.TaskUpsertDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Mappers.Task.TaskUpsertMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskUpsertMapperTest {

    private final TaskUpsertMapper taskUpsertMapper;

    @Autowired
    public TaskUpsertMapperTest(TaskUpsertMapper taskUpsertMapper) {
        this.taskUpsertMapper = taskUpsertMapper;
    }

    @Test
    public void TaskUpsertDtoToEntityTest(){

        TaskUpsertDto upsertDto = new TaskUpsertDto();
        upsertDto.setTaskName("typeName");
        upsertDto.setTaskStatusId(1L);
        upsertDto.setTaskTypeId(2L);
        upsertDto.setDescription("description");

        TaskEntity taskEntity = this.taskUpsertMapper.toEntity(upsertDto);
        assertEquals("typeName", taskEntity.getTaskName());
        assertEquals(1L, taskEntity.getTaskStatusId());
        assertEquals(2L, taskEntity.getTaskTypeId());
        assertEquals("description", taskEntity.getDescription());
    }

    @Test
    public void updateEntityTest(){

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName("typeName");
        taskEntity.setTaskStatusId(1L);
        taskEntity.setTaskTypeId(2L);
        taskEntity.setDescription("description");

        TaskUpsertDto upsertDto = new TaskUpsertDto();
        upsertDto.setTaskName("newTypeName");
        upsertDto.setTaskTypeId(5L);
        upsertDto.setDescription("other description");

        TaskEntity updatedEntity = this.taskUpsertMapper.update(taskEntity, upsertDto);
        assertEquals("newTypeName", updatedEntity.getTaskName());
        assertEquals(1L, updatedEntity.getTaskStatusId());
        assertEquals(5L, updatedEntity.getTaskTypeId());
        assertEquals("other description", updatedEntity.getDescription());
    }
}
