package it61.springlabs.hostingQueueConsumer.Models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Where(clause = "is_deleted = 0")
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
    private String phone;
    @Column
    private String secret;
    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column
    private boolean is_deleted = false;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Vps> servers;

    protected User(){}

    public User(String username, String description, String phone, String secret) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.description = description;
        this.phone = phone;
        this.secret = secret;
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

    public String getSecret() {
        return secret;
    }

    public User setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public User setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
        return this;
    }
}