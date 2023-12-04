package JeysonAmadoA.TaskManager.Integration.Services;

import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Dto.Task.TaskUpsertDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Exception.Task.CreateTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.DeleteTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.UpdateTaskException;
import JeysonAmadoA.TaskManager.Mappers.Task.TaskMapper;
import JeysonAmadoA.TaskManager.Mappers.Task.TaskUpsertMapper;
import JeysonAmadoA.TaskManager.Repositories.Task.TaskRepository;
import JeysonAmadoA.TaskManager.Services.Task.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepo;

    @Mock
    private TaskUpsertMapper upsertMapper;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void createTaskTest() throws Exception {
        TaskEntity task = new TaskEntity();
        TaskUpsertDto upsertDto = new TaskUpsertDto();

        when(this.upsertMapper.toEntity(any(TaskUpsertDto.class))).thenReturn(task);
        when(this.taskRepo.save(any(TaskEntity.class))).thenReturn(task);

        TaskUpsertDto createdTask = this.taskService.createTask(upsertDto);
        verify(this.upsertMapper, times(1)).toEntity(upsertDto);
        verify(this.taskRepo, times(1)).save(task);
        assertEquals(upsertDto, createdTask);
    }

    @Test
    public void createTaskFailTest() {
        TaskUpsertDto upsertDto = new TaskUpsertDto();

        when(this.upsertMapper.toEntity(any(TaskUpsertDto.class))).thenReturn(new TaskEntity());
        when(this.taskRepo.save(any(TaskEntity.class))).thenThrow(new RuntimeException("Error en tiempo de ejecución"));

        try {
            this.taskService.createTask(upsertDto);
        } catch (CreateTaskException e) {
            assertEquals("Error al crear la tarea. Error en tiempo de ejecución", e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getTaskByIdTest(){
        TaskEntity task = new TaskEntity();
        TaskDto taskDto = new TaskDto();
        Long taskId = 1L;

        when(this.taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(this.taskMapper.toDto(any(TaskEntity.class))).thenReturn(taskDto);

        TaskDto foundTask = this.taskService.getTaskById(taskId);
        verify(this.taskRepo, times(1)).findById(1L);
        verify(this.taskMapper, times(1)).toDto(task);
        assertEquals(taskDto, foundTask);
    }

    @Test
    public void getAllTasksTest(){
        TaskEntity taskOne = new TaskEntity();
        TaskEntity taskTwo = new TaskEntity();

        TaskDto taskDtoOne = new TaskDto();
        TaskDto taskDtoTwo = new TaskDto();

        List<TaskEntity> taskEntityList = Arrays.asList(taskOne, taskTwo);
        List<TaskDto> taskDtoList = Arrays.asList(taskDtoOne, taskDtoTwo);

        when(this.taskRepo.findAll()).thenReturn(taskEntityList);
        when(this.taskMapper.toDto(taskOne)).thenReturn(taskDtoOne);
        when(this.taskMapper.toDto(taskTwo)).thenReturn(taskDtoTwo);

        List<TaskDto> allTask = this.taskService.getAllTasks();

        verify(this.taskRepo, times(1)).findAll();
        verify(this.taskMapper, times(1)).toDto(taskOne);
        verify(this.taskMapper, times(1)).toDto(taskTwo);
        assertEquals(taskDtoList, allTask);
    }

    @Test
    public void getTasksByStatusTest(){
        TaskEntity taskOne = new TaskEntity();
        TaskEntity taskTwo = new TaskEntity();

        TaskDto taskDtoOne = new TaskDto();
        TaskDto taskDtoTwo = new TaskDto();

        Long statusId = 1L;

        List<TaskEntity> taskEntityList = Arrays.asList(taskOne, taskTwo);
        List<TaskDto> taskDtoList = Arrays.asList(taskDtoOne, taskDtoTwo);

        when(this.taskRepo.findByTaskStatusId(statusId)).thenReturn(taskEntityList);
        when(this.taskMapper.toDto(taskOne)).thenReturn(taskDtoOne);
        when(this.taskMapper.toDto(taskTwo)).thenReturn(taskDtoTwo);

        List<TaskDto> statusTasks = this.taskService.getTasksByStatus(statusId);

        verify(this.taskRepo, times(1)).findByTaskStatusId(statusId);
        verify(this.taskMapper, times(1)).toDto(taskOne);
        verify(this.taskMapper, times(1)).toDto(taskTwo);
        assertEquals(taskDtoList, statusTasks);
    }

    @Test
    public void getTasksByTypeTest(){
        TaskEntity taskOne = new TaskEntity();
        TaskEntity taskTwo = new TaskEntity();

        TaskDto taskDtoOne = new TaskDto();
        TaskDto taskDtoTwo = new TaskDto();

        Long typeId = 1L;

        List<TaskEntity> taskEntityList = Arrays.asList(taskOne, taskTwo);
        List<TaskDto> taskDtoList = Arrays.asList(taskDtoOne, taskDtoTwo);

        when(this.taskRepo.findByTaskTypeId(typeId)).thenReturn(taskEntityList);
        when(this.taskMapper.toDto(taskOne)).thenReturn(taskDtoOne);
        when(this.taskMapper.toDto(taskTwo)).thenReturn(taskDtoTwo);

        List<TaskDto> typeTasks = this.taskService.getTasksByType(typeId);

        verify(this.taskRepo, times(1)).findByTaskTypeId(typeId);
        verify(this.taskMapper, times(1)).toDto(taskOne);
        verify(this.taskMapper, times(1)).toDto(taskTwo);
        assertEquals(taskDtoList, typeTasks);
    }

    @Test
    public void setCompleteTaskTest() throws UpdateTaskException {
        TaskEntity task = new TaskEntity();
        Long taskId = 1L;

        when(this.taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(this.taskRepo.save(any(TaskEntity.class))).thenReturn(task);

        boolean isCompleted = this.taskService.setCompleteTask(taskId);

        verify(this.taskRepo, times(1)).findById(taskId);
        verify(this.taskRepo, times(1)).save(task);
        assertTrue(isCompleted);
    }

    @Test
    public void setCompleteTaskFailTest() {
        TaskEntity task = new TaskEntity();

        Long taskId = 1L;

        when(this.taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(this.taskRepo.save(any(TaskEntity.class))).thenThrow(new RuntimeException("Error en tiempo de ejecución"));

        try {
            this.taskService.setCompleteTask(taskId);
        } catch (UpdateTaskException e) {
            assertEquals("Error al actualizar la tarea. Error en tiempo de ejecución", e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateTaskTest() throws UpdateTaskException {
        TaskEntity task = new TaskEntity();
        TaskUpsertDto upsertDto = new TaskUpsertDto();
        TaskDto taskDto = new TaskDto();

        Long taskId = 1L;

        when(this.taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(this.upsertMapper.update(any(TaskEntity.class),any(TaskUpsertDto.class))).thenReturn(task);
        when(this.taskRepo.save(any(TaskEntity.class))).thenReturn(task);
        when(this.taskMapper.toDto(any(TaskEntity.class))).thenReturn(taskDto);

        TaskDto updatedTask = this.taskService.updateTask(taskId, upsertDto);
        verify(this.taskRepo, times(1)).findById(taskId);
        verify(this.upsertMapper, times(1)).update(task, upsertDto);
        verify(this.taskRepo, times(1)).save(task);
        verify(this.taskMapper, times(1)).toDto(task);
        assertEquals(taskDto, updatedTask);
    }

    @Test
    public void updateTaskFailTest() {
        TaskEntity task = new TaskEntity();
        TaskUpsertDto upsertDto = new TaskUpsertDto();

        Long taskId = 1L;

        when(this.taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(this.upsertMapper.update(any(TaskEntity.class),any(TaskUpsertDto.class))).thenReturn(task);
        when(this.taskRepo.save(any(TaskEntity.class))).thenThrow(new RuntimeException("Error en tiempo de ejecución"));

        try {
            this.taskService.updateTask(taskId, upsertDto);
        } catch (UpdateTaskException e) {
            assertEquals("Error al actualizar la tarea. Error en tiempo de ejecución", e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteTaskTest() throws DeleteTaskException {
        TaskEntity task = new TaskEntity();

        Long taskId = 1L;

        when(this.taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(this.taskRepo.save(any(TaskEntity.class))).thenReturn(task);

        boolean isDeleted = this.taskService.deleteTask(taskId);
        verify(this.taskRepo, times(1)).findById(taskId);
        verify(this.taskRepo, times(1)).save(task);
        assertTrue(isDeleted);
    }

    @Test
    public void deleteTaskFailTest() {
        TaskEntity task = new TaskEntity();

        Long taskId = 1L;

        when(this.taskRepo.findById(taskId)).thenReturn(Optional.of(task));
        when(this.taskRepo.save(any(TaskEntity.class))).thenThrow(new RuntimeException("Error en tiempo de ejecución"));

        try {
            this.taskService.deleteTask(taskId);
        } catch (DeleteTaskException e) {
            assertEquals("Error al eliminar la tarea. Error en tiempo de ejecución", e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
