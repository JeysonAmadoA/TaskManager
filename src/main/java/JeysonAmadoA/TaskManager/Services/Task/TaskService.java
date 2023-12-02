package JeysonAmadoA.TaskManager.Services.Task;

import JeysonAmadoA.TaskManager.Dto.Task.TaskUpsertDto;
import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Exception.Task.CreateTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.DeleteTaskException;
import JeysonAmadoA.TaskManager.Exception.Task.UpdateTaskException;
import JeysonAmadoA.TaskManager.Interfaces.Services.Task.TaskServiceInterface;
import JeysonAmadoA.TaskManager.Mappers.Task.TaskUpsertMapper;
import JeysonAmadoA.TaskManager.Mappers.Task.TaskMapper;
import JeysonAmadoA.TaskManager.Repositories.Task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements TaskServiceInterface {

    private final TaskRepository taskRepo;
    private final TaskMapper taskMapper;
    private final TaskUpsertMapper taskUpsertMapper;

    @Autowired
    public TaskService(TaskRepository taskRepo, TaskMapper taskMapper, TaskUpsertMapper taskUpsertMapper) {
        this.taskRepo = taskRepo;
        this.taskMapper = taskMapper;
        this.taskUpsertMapper = taskUpsertMapper;
    }


    @Override
    public TaskUpsertDto createTask(TaskUpsertDto taskUpsertDto) throws Exception {
        try {
            TaskEntity task = this.taskUpsertMapper.toEntity(taskUpsertDto);
            task.commitCreate();
            this.taskRepo.save(task);
        }catch (Exception e){
            throw new CreateTaskException(e.getMessage());
        }
        return taskUpsertDto;
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        TaskEntity task = this.taskRepo.findById(taskId).orElse(null);
        return task != null ? taskMapper.toDto(task) : null;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<TaskEntity> tasks = this.taskRepo.findAll();
        return tasks.stream()
                .map(this.taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTasksByStatus(Long statusId) {
        List<TaskEntity> tasks = this.taskRepo.findByTaskStatusId(statusId);
        return tasks.stream()
                .map(this.taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getTasksByType(Long taskTypeId) {
        List<TaskEntity> tasks = this.taskRepo.findByTaskTypeId(taskTypeId);
        return tasks.stream()
                .map(this.taskMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskUpsertDto updateTaskDto) throws UpdateTaskException {
        try {
            TaskEntity task = this.taskRepo.findById(taskId).orElse(null);
            if (task != null){
                TaskEntity updatedTask = this.taskUpsertMapper.update(task, updateTaskDto);
                updatedTask.commitUpdate();
                this.taskRepo.save(updatedTask);
                return this.taskMapper.toDto(updatedTask);
            } else return null;
        }catch (Exception e){
            throw new UpdateTaskException(e.getMessage());
        }
    }

    @Override
    public boolean deleteTask(Long taskId) throws DeleteTaskException {
        try {
            TaskEntity taskToDelete = this.taskRepo.findById(taskId).orElse(null);
            if (taskToDelete != null){
                taskToDelete.commitDelete();
                this.taskRepo.save(taskToDelete);
                return true;
            } else return false;

        }catch (Exception e){
            throw new DeleteTaskException(e.getMessage());
        }
    }
}
