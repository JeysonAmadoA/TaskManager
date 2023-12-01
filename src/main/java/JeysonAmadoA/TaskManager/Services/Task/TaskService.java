package JeysonAmadoA.TaskManager.Services.Task;

import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Exception.Task.CreateTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.DeleteTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.UpdateTaskException;
import JeysonAmadoA.TaskManager.Interfaces.Services.Task.TaskServiceInterface;
import JeysonAmadoA.TaskManager.Mappers.Task.TaskMapper;
import JeysonAmadoA.TaskManager.Repositories.Task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServiceInterface {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }


    @Override
    public TaskDto createTask(TaskDto taskDto) throws Exception {
        try {
            TaskEntity task = this.taskMapper.toEntity(taskDto);
            task.commitCreate();
            this.taskRepository.save(task);
        }catch (Exception e){
            throw new CreateTaskException(e.getMessage());
        }
        return taskDto;
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        TaskEntity task = this.taskRepository.findById(taskId).orElse(null);
        return task != null ? taskMapper.toDto(task) : null;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<TaskEntity> tasks = this.taskRepository.findAll();
        return tasks.stream()
                .map(this.taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTasksByStatus(Long statusId) {
        List<TaskEntity> tasks = this.taskRepository.findByTaskStatusId(statusId);
        return tasks.stream()
                .map(this.taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTasksByType(Long taskTypeId) {
        List<TaskEntity> tasks = this.taskRepository.findByTaskTypeId(taskTypeId);
        return tasks.stream()
                .map(this.taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto taskDto) throws UpdateTaskException {
        try {
            TaskEntity task = this.taskRepository.findById(taskId).orElse(null);
            if (task != null){
                TaskEntity updatedTask = this.taskMapper.update(task, taskDto);
                updatedTask.commitUpdate();
                this.taskRepository.save(updatedTask);
                return this.taskMapper.toDto(updatedTask);
            } else return null;
        }catch (Exception e){
            throw new UpdateTaskException(e.getMessage());
        }
    }

    @Override
    public boolean deleteTask(Long taskId) throws DeleteTaskException {
        try {
            TaskEntity taskToDelete = this.taskRepository.findById(taskId).orElse(null);
            if (taskToDelete != null){
                taskToDelete.commitDelete();
                this.taskRepository.save(taskToDelete);
                return true;
            } else return false;

        }catch (Exception e){
            throw new DeleteTaskException(e.getMessage());
        }
    }
}
