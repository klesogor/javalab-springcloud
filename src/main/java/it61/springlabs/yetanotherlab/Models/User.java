package it61.springlabs.yetanotherlab.Models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "users")
@Where(clause = "is_deleted=0")
public final class User {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @Column
    private String username;
    @Column
    private String description;
    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column
    private Boolean is_deleted;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Vps> servers;

    protected User(){}

    public User(String username, String description) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.description = description;
        this.is_deleted = false;

    }

    public UUID getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public User setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Vps> getServers() {
        return servers;
    }
}