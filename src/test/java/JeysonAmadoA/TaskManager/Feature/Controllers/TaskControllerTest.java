package JeysonAmadoA.TaskManager.Feature.Controllers;

import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Dto.Task.TaskUpsertDto;
import JeysonAmadoA.TaskManager.Exception.Task.DeleteTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.UpdateTaskException;
import JeysonAmadoA.TaskManager.Http.Controller.TaskController;
import JeysonAmadoA.TaskManager.Interfaces.Services.Task.TaskServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {

    @Mock
    private TaskServiceInterface taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    public void createTaskTest() throws Exception {
        TaskUpsertDto upsertDto = new TaskUpsertDto();
        upsertDto.setTaskName("newTask");

        when(this.taskService.createTask(any(TaskUpsertDto.class))).thenReturn(upsertDto);

        ResponseEntity<String> response = this.taskController.createTask(upsertDto);

        verify(this.taskService, times(1)).createTask(upsertDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Tarea newTask creada con exito", response.getBody());
    }

    @Test
    public void getTaskTest(){
        Long taskId = 1L;
        TaskDto TaskDto = new TaskDto();
        TaskDto.setId(taskId);

        when(this.taskService.getTaskById(any(Long.class))).thenReturn(TaskDto);

        ResponseEntity<TaskDto> response = this.taskController.getTask(taskId);

        verify(this.taskService, times(1)).getTaskById(taskId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getTaskNotFoundTest(){
        Long taskId = 1L;

        when(this.taskService.getTaskById(any(Long.class))).thenReturn(null);

        ResponseEntity<TaskDto> response = this.taskController.getTask(taskId);

        verify(this.taskService, times(1)).getTaskById(taskId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void gatAllTaskTest(){
        TaskDto TaskDtoOne = new TaskDto();
        TaskDto TaskDtoTwo = new TaskDto();

        List<TaskDto> allTasks = Arrays.asList(TaskDtoOne, TaskDtoTwo);

        when(this.taskService.getAllTasks()).thenReturn(allTasks);
        ResponseEntity<List<TaskDto>> response = this.taskController.getAllTask();

        verify(this.taskService, times(1)).getAllTasks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void filterByStatusTest(){
        Long statusId = 1L;
        TaskDto TaskDtoOne = new TaskDto();
        TaskDto TaskDtoTwo = new TaskDto();

        List<TaskDto> filteredByStatusTasks = Arrays.asList(TaskDtoOne, TaskDtoTwo);

        when(this.taskService.getTasksByStatus(any(Long.class))).thenReturn(filteredByStatusTasks);
        ResponseEntity<List<TaskDto>> response = this.taskController.filterByStatus(statusId);

        verify(this.taskService, times(1)).getTasksByStatus(statusId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void filterByTypeTest(){
        Long typeId = 1L;
        TaskDto TaskDtoOne = new TaskDto();
        TaskDto TaskDtoTwo = new TaskDto();

        List<TaskDto> filteredByTypeTasks = Arrays.asList(TaskDtoOne, TaskDtoTwo);

        when(this.taskService.getTasksByType(any(Long.class))).thenReturn(filteredByTypeTasks);
        ResponseEntity<List<TaskDto>> response = this.taskController.filterByType(typeId);

        verify(this.taskService, times(1)).getTasksByType(typeId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void completeTaskTest() throws UpdateTaskException {

        Long taskId = 1L;

        when(this.taskService.setCompleteTask(any(Long.class))).thenReturn(true);

        ResponseEntity<String> response = this.taskController.completeTask(taskId);

        verify(this.taskService, times(1)).setCompleteTask(taskId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tarea completada", response.getBody());
    }

    @Test
    public void completeTaskNotFoundTest() throws UpdateTaskException {

        Long taskId = 1L;

        when(this.taskService.setCompleteTask(any(Long.class))).thenReturn(false);
        ResponseEntity<String> response = this.taskController.completeTask(taskId);

        verify(this.taskService, times(1)).setCompleteTask(taskId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void updateTaskTest() throws UpdateTaskException {

        Long taskId = 1L;
        TaskUpsertDto upsertDto = new TaskUpsertDto();
        TaskDto taskDto = new TaskDto();

        when(this.taskService.updateTask(any(Long.class), any(TaskUpsertDto.class))).thenReturn(taskDto);
        ResponseEntity<String> response = this.taskController.updateTask(taskId, upsertDto);

        verify(this.taskService, times(1)).updateTask(taskId, upsertDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tarea actualizada con exito", response.getBody());
    }

    @Test
    public void updateTaskNotFoundTest() throws UpdateTaskException {

        Long taskId = 1L;
        TaskUpsertDto upsertDto = new TaskUpsertDto();

        when(this.taskService.updateTask(any(Long.class), any(TaskUpsertDto.class))).thenReturn(null);
        ResponseEntity<String> response = this.taskController.updateTask(taskId, upsertDto);

        verify(this.taskService, times(1)).updateTask(taskId, upsertDto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteTaskTest() throws DeleteTaskException {

        Long taskId = 1L;

        when(this.taskService.deleteTask(any(Long.class))).thenReturn(true);
        ResponseEntity<String> response = this.taskController.deleteTask(taskId);

        verify(this.taskService, times(1)).deleteTask(taskId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Tarea eliminada con exito", response.getBody());
    }

    @Test
    public void deleteTaskNotFoundTest() throws DeleteTaskException {

        Long taskId = 1L;

        when(this.taskService.deleteTask(any(Long.class))).thenReturn(false);
        ResponseEntity<String> response = this.taskController.deleteTask(taskId);

        verify(this.taskService, times(1)).deleteTask(taskId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
