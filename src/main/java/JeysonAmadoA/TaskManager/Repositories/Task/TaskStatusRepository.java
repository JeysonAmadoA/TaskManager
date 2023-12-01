package JeysonAmadoA.TaskManager.Repositories.Task;

import JeysonAmadoA.TaskManager.Entities.Task.TaskStatusEntity;
import JeysonAmadoA.TaskManager.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends BaseRepository<TaskStatusEntity, Long> {
}
