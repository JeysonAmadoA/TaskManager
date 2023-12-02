package JeysonAmadoA.TaskManager.Integration.Repositories;

import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Entities.Task.TaskStatusEntity;
import JeysonAmadoA.TaskManager.Entities.Task.TaskTypeEntity;
import JeysonAmadoA.TaskManager.Repositories.Task.TaskRepository;
import JeysonAmadoA.TaskManager.Repositories.Task.TaskStatusRepository;
import JeysonAmadoA.TaskManager.Repositories.Task.TaskTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepositoryTest {

    private final TaskRepository taskRepo;

    private final TaskStatusRepository taskStatusRepo;

    private final TaskTypeRepository taskTypeRepo;

    @Autowired
    public TaskRepositoryTest(TaskRepository taskRepo, TaskStatusRepository taskStatusRepository, TaskTypeRepository taskTypeRepo) {
        this.taskRepo = taskRepo;
        this.taskStatusRepo = taskStatusRepository;
        this.taskTypeRepo = taskTypeRepo;
    }

    @Test
    public void saveAndGetTaskTest() {

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName("typeName");
        taskEntity.setTaskStatusId(2L);
        taskEntity.setTaskTypeId(1L);
        taskEntity.setDescription("description");

        this.taskRepo.save(taskEntity);
        TaskEntity taskFound = this.taskRepo.findById(taskEntity.getId()).orElse(null);

        assertNotNull(taskFound);
        assertEquals("typeName", taskFound.getTaskName());
        assertEquals(2L, taskFound.getTaskStatusId());
        assertEquals(1L, taskFound.getTaskTypeId());
        assertEquals("description", taskFound.getDescription());
    }

    @Test
    public void updateTaskTest() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName("typeName");
        taskEntity.setTaskStatusId(2L);
        taskEntity.setTaskTypeId(1L);
        taskEntity.setDescription("description");

        this.taskRepo.save(taskEntity);
        TaskEntity taskFound = this.taskRepo.findById(taskEntity.getId()).orElse(null);
        assertNotNull(taskFound);

        taskFound.setTaskName("newTaskName");
        taskFound.setDescription("newDescription");
        this.taskRepo.save(taskFound);
        TaskEntity taskUpdated = this.taskRepo.findById(taskEntity.getId()).orElse(null);

        assertNotNull(taskUpdated);
        assertEquals("newTaskName", taskUpdated.getTaskName());
        assertEquals(2L, taskUpdated.getTaskStatusId());
        assertEquals(1L, taskUpdated.getTaskTypeId());
        assertEquals("newDescription", taskUpdated.getDescription());
    }

    @Test
    public void findByTaskStatusIdTest() {
        TaskStatusEntity inProcess = new TaskStatusEntity();
        inProcess.setName("En proceso");
        inProcess.setFinished(true);
        this.taskStatusRepo.save(inProcess);

        TaskStatusEntity canceled = new TaskStatusEntity();
        canceled.setName("Cancelado");
        canceled.setFinished(false);
        this.taskStatusRepo.save(canceled);

        TaskTypeEntity homeType = new TaskTypeEntity();
        homeType.setName("Hogar");
        this.taskTypeRepo.save(homeType);

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskName("typeName");
        taskEntity.setTaskStatusId(2L);
        taskEntity.setTaskTypeId(1L);

        this.taskRepo.save(taskEntity);
        List<TaskEntity> tasksByStatus = this.taskRepo.findByTaskStatusId(2L);
        assertFalse(tasksByStatus.isEmpty());

        List<TaskEntity> anotherTasksByStatus = this.taskRepo.findByTaskStatusId(3L);
        assertTrue(anotherTasksByStatus.isEmpty());
    }



}
