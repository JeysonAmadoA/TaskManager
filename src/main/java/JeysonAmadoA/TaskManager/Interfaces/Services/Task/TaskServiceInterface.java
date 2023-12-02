package JeysonAmadoA.TaskManager.Interfaces.Services.Task;

import JeysonAmadoA.TaskManager.Dto.Task.TaskUpsertDto;
import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Exception.Task.DeleteTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.UpdateTaskException;

import java.util.List;

public interface TaskServiceInterface {

    TaskUpsertDto createTask(TaskUpsertDto taskUpsertDto) throws Exception;

    TaskDto getTaskById(Long taskId);

    List<TaskDto> getAllTasks();

    List<TaskDto> getTasksByStatus(Long statusId);

    List<TaskDto> getTasksByType(Long taskTypeId);

    TaskDto updateTask(Long taskId, TaskUpsertDto updateTaskDto) throws UpdateTaskException;

    boolean deleteTask(Long taskId) throws DeleteTaskException;
}
