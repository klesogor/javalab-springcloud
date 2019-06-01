package it61.springlabs.data.dto.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public final class UserRegistrationDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String secret;
    @NotNull
    @JsonProperty(value = "is_admin")
    private boolean admin;

    public UserRegistrationDTO(String username, String password, String secret, boolean admin) {
        this.username = username;
        this.password = password;
        this.secret = secret;
        this.admin = admin;
    }

    public UserRegistrationDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getSecret() {
        return secret;
    }

    public String getPassword() { return password; }

    public boolean isAdmin() { return admin; }
}
