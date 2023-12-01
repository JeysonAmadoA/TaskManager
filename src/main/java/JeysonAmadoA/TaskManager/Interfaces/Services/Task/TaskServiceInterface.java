package JeysonAmadoA.TaskManager.Interfaces.Services.Task;

import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Exception.Task.CreateTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.DeleteTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.UpdateTaskException;

import java.util.List;

public interface TaskServiceInterface {

    public TaskDto createTask(TaskDto taskDto) throws Exception;

    public TaskDto getTaskById(Long taskId);

    public List<TaskDto> getAllTasks();

    public List<TaskDto> getTasksByStatus(Long statusId);

    public List<TaskDto> getTasksByType(Long taskTypeId);

    public TaskDto updateTask(Long taskId, TaskDto taskDto) throws UpdateTaskException;

    public boolean deleteTask(Long taskId) throws DeleteTaskException;
}
