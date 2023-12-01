package JeysonAmadoA.TaskManager.Entities.Task;

import JeysonAmadoA.TaskManager.Entities.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

@EqualsAndHashCode(callSuper = true)
@Entity
@Where(clause = "deleted_at is NULL")
@Table(name = "tasks")
@Data
public class TaskEntity extends BaseEntity {

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "task_type_id", nullable = false)
    private Long taskTypeId;

    @ManyToOne
    @JoinColumn(name = "task_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TaskTypeEntity taskType;

    @Column(name = "task_status_id", nullable = false)
    private Long taskStatusId;

    @ManyToOne
    @JoinColumn(name = "task_status_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TaskStatusEntity taskStatus;

    @Column
    private String description;

}
