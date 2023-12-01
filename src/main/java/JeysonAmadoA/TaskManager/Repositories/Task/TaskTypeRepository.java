package JeysonAmadoA.TaskManager.Repositories.Task;

import JeysonAmadoA.TaskManager.Entities.Task.TaskTypeEntity;
import JeysonAmadoA.TaskManager.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskTypeRepository extends BaseRepository<TaskTypeEntity, Long> {
}
