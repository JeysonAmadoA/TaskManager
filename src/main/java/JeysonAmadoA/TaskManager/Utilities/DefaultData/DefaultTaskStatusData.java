package JeysonAmadoA.TaskManager.Utilities.DefaultData;

import JeysonAmadoA.TaskManager.Entities.Task.TaskStatusEntity;
import JeysonAmadoA.TaskManager.Repositories.Task.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultTaskStatusData implements CommandLineRunner {


    private final TaskStatusRepository taskStatusRepository;

    @Autowired
    public DefaultTaskStatusData(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    public void run(String... args) {
        TaskStatusEntity inProcess = new TaskStatusEntity();
        inProcess.setName("En proceso");
        inProcess.setFinished(false);
        inProcess.commitCreate();

        TaskStatusEntity completed = new TaskStatusEntity();
        completed.setName("Completado");
        completed.setFinished(true);
        completed.commitCreate();

        TaskStatusEntity canceled = new TaskStatusEntity();
        canceled.setName("Cancelado");
        canceled.setFinished(true);
        canceled.commitCreate();

        this.taskStatusRepository.save(inProcess);
        this.taskStatusRepository.save(completed);
        this.taskStatusRepository.save(canceled);
    }
}
