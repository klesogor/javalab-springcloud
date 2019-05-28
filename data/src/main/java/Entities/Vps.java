package Entities;

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
@Where(clause = "is_deleted = 0")
public final class Vps {
    @Id
    @Column(name ="id", insertable = false, updatable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @JoinColumn
    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @Column
    private String operatingSystem;

    @Column
    private Integer CPUCount;

    @Column
    private Double CPURate;

    @Column
    private Double RAM;
    @Column
    private boolean is_deleted = false;


    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Vps(){}

    public Vps(User owner, String operatingSystem,Integer cpuCount, Double cpuRate, Double RAM ) {
        this.id = UUID.randomUUID();
        this.owner = owner;
        this.operatingSystem = operatingSystem;
        this.CPURate = cpuRate;
        this.CPUCount = cpuCount;
        this.RAM = RAM;
    }

    public Vps(String operatingSystem){
        this.id = UUID.randomUUID();
        this.operatingSystem = operatingSystem;
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

    public Vps setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Vps setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public Integer getCPUCount() {
        return CPUCount;
    }

    public Vps setCPUCount(Integer CPUCount) {
        this.CPUCount = CPUCount;
        return this;
    }

    public Double getCPURate() {
        return CPURate;
    }

    public Vps setCPURate(Double CPURate) {
        this.CPURate = CPURate;
        return this;
    }

    public Double getRAM() {
        return RAM;
    }

    public Vps setRAM(Double RAM) {
        this.RAM = RAM;
        return this;
    }

    public boolean getIsDeleted() {
        return  this.is_deleted;
    }

    public Vps setIsDeleted(boolean is_deleted) {
        this.is_deleted = true;
        return this;
    }
}
