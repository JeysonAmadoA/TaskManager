package JeysonAmadoA.TaskManager.Http.Controller;

import JeysonAmadoA.TaskManager.Dto.Task.TaskDto;
import JeysonAmadoA.TaskManager.Interfaces.Services.Task.TaskServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {


    private final TaskServiceInterface taskService;
    @Autowired
    public TaskController(TaskServiceInterface taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody TaskDto taskDto) {
        try {
            TaskDto createdTask = this.taskService.createTask(taskDto);
            String response = "Tarea " + createdTask.getTaskName() + " creada con exito";
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        TaskDto task = this.taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> gatAllTask() {
        List<TaskDto> allTasks = this.taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/status/{statusId}")
    public ResponseEntity<List<TaskDto>> filterByStatus(@PathVariable Long statusId) {
        List<TaskDto> tasksByStatus = this.taskService.getTasksByStatus(statusId);
        return ResponseEntity.ok(tasksByStatus);
    }

    @GetMapping("/categories/{taskTypeId}")
    public ResponseEntity<List<TaskDto>> filterByType(@PathVariable Long taskTypeId) {
        List<TaskDto> tasksByType = this.taskService.getTasksByType(taskTypeId);
        return ResponseEntity.ok(tasksByType);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody TaskDto updateDto) {
        try {
            TaskDto updatedTask = this.taskService.updateTask(id, updateDto);
            return updatedTask != null
                    ? ResponseEntity.status(HttpStatus.CREATED).body("Tarea actualizada con exito")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        try {
            boolean isDeleted = this.taskService.deleteTask(id);
            return isDeleted
                    ? ResponseEntity.status(HttpStatus.CREATED).body("Tarea eliminada con exito")
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarea no encontrada");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
