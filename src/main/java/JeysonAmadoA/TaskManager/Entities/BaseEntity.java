package JeysonAmadoA.TaskManager.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void commitDelete() {
        this.setDeletedAt(LocalDateTime.now()) ;
    }

    public void commitUpdate(Long userId) {
        this.setUpdatedAt(LocalDateTime.now());
    }

    public void commitCreate(Long userId) {
        this.setCreatedAt(LocalDateTime.now());
    }

}