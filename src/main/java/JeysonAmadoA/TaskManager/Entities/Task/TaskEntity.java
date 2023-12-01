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

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_type_id")
    private Long taskTypeId;

    @ManyToOne
    @JoinColumn(name = "task_type_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TaskTypeEntity taskType;

    @Column(name = "task_status_id")
    private Long taskStatusId;

    @ManyToOne
    @JoinColumn(name = "task_status_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TaskTypeEntity taskStatus;

    @Column
    private String description;

}
