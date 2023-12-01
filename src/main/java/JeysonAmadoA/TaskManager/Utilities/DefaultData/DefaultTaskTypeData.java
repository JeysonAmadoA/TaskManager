package JeysonAmadoA.TaskManager.Utilities.DefaultData;

import JeysonAmadoA.TaskManager.Entities.Task.TaskTypeEntity;
import JeysonAmadoA.TaskManager.Repositories.Task.TaskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultTaskTypeData implements CommandLineRunner {


    private final TaskTypeRepository taskTypeRepo;

    @Autowired
    public DefaultTaskTypeData(TaskTypeRepository taskStatusRepository) {
        this.taskTypeRepo = taskStatusRepository;
    }

    @Override
    public void run(String... args) {
        TaskTypeEntity homeType = new TaskTypeEntity();
        homeType.setName("Hogar");
        homeType.commitCreate();

        TaskTypeEntity universityType = new TaskTypeEntity();
        universityType.setName("Universidad");
        universityType.commitCreate();

        TaskTypeEntity workType = new TaskTypeEntity();
        workType.setName("Trabajo");
        workType.commitCreate();

        this.taskTypeRepo.save(homeType);
        this.taskTypeRepo.save(universityType);
        this.taskTypeRepo.save(workType);
    }
}
