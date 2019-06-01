package it61.springlabs.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
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
    @NotNull
    private String username;

    @Column
    @JsonIgnore
    @NotNull
    private String password;

    @Column
    private String secret;

    @Column
    @NotNull
    private String roles;

    @Column
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    private boolean deleted = false;

    @OneToOne(targetEntity = AccountDetails.class,fetch = FetchType.LAZY)
    private AccountDetails accountDetails;

    protected User(){}

    public User(UUID id, String username, String password, String secret, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.secret = secret;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String[] getRolesDecoded(){
        return  roles.split(",");
    }

    public void setRoles(String[] roles){
        this.roles = String.join(",", roles);
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }
}