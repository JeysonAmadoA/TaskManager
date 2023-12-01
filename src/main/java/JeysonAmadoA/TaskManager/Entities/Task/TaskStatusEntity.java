package JeysonAmadoA.TaskManager.Entities.Task;

import JeysonAmadoA.TaskManager.Entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;


@EqualsAndHashCode(callSuper = true)
@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "task_status")
@Data
public class TaskStatusEntity extends BaseEntity {

    @Column
    private String name;

    @Column(name = "is_finished")
    private boolean isFinished;

}
