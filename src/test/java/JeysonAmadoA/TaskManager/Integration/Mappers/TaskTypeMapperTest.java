package JeysonAmadoA.TaskManager.Integration.Mappers;

import JeysonAmadoA.TaskManager.Dto.Task.TaskTypeDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskTypeEntity;
import JeysonAmadoA.TaskManager.Mappers.Task.TaskTypeMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskTypeMapperTest {

    private final TaskTypeMapper typeMapper;

    @Autowired
    public TaskTypeMapperTest(TaskTypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Test
    public void TaskTypeDtoToEntityTest(){

        TaskTypeDto typeDto = new TaskTypeDto();
        typeDto.setName("typeName");

        TaskTypeEntity taskTypeEntity = this.typeMapper.toEntity(typeDto);
        assertEquals("typeName", taskTypeEntity.getName());
    }

    @Test
    public void TaskTypeEntityToDtoTest(){

        TaskTypeEntity taskTypeEntity = new TaskTypeEntity();
        taskTypeEntity.setName("typeName");

        TaskTypeDto typeDto = this.typeMapper.toDto(taskTypeEntity);
        assertEquals("typeName", typeDto.getName());
    }
}
