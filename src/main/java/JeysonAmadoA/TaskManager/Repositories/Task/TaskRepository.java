package JeysonAmadoA.TaskManager.Repositories.Task;

import JeysonAmadoA.TaskManager.Entities.Task.TaskEntity;
import JeysonAmadoA.TaskManager.Repositories.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends BaseRepository<TaskEntity, Long> {

    List<TaskEntity> findByTaskStatusId(Long statusId);
    List<TaskEntity> findByTaskTypeId(Long taskTypeId);

}
