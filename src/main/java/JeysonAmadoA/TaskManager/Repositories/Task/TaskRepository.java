package JeysonAmadoA.TaskManager.Repositories.Task;

import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseRepository<TaskEntity, Long> {
}
