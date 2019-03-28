package it61.springlabs.eurekaclient.DTO;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class UserDTO {
    @NotNull
    private String username;
    @NotNull
    private String description;
    @NotNull
    private String phone;
    @NotNull
    private String secret;

    public UserDTO(String username, String description, String phone, String secret) {
        this.username = username;
        this.description = description;
        this.phone = phone;
        this.secret = secret;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getSecret() {
        return secret;
    }

    public String getPhone() {
        return phone;
    }
}
