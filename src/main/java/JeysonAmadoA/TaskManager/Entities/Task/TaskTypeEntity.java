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
@Table(name = "task_types")
@Data
public class TaskTypeEntity extends BaseEntity {

    @Column
    private String name;
}
