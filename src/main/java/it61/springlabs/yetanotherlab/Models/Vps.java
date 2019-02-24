package it61.springlabs.yetanotherlab.Models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vps")
@Where(clause = "is_deleted=0")
public final class Vps {
    @Id
    @Column(name ="id", insertable = false, updatable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @JoinColumn
    @Nullable
    @ManyToOne
    private User owner;

    @Column
    private String operatingSystem;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    private Boolean is_deleted;

    protected Vps(){}

    public Vps(User owner, String operatingSystem) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.operatingSystem = operatingSystem;
        this.is_deleted = false;
    }

    public Vps(String operatingSystem){
        this.id = UUID.randomUUID();
        this.operatingSystem = operatingSystem;
        this.is_deleted = false;
    }

    public UUID getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public Vps setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Vps setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public Vps setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
        return this;
    }
}
