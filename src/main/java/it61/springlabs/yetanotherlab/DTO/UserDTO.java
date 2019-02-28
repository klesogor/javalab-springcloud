package it61.springlabs.yetanotherlab.DTO;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class UserDTO {
    @NotNull
    private String username;
    @NotNull
    private String description;

    public UserDTO(String username, String description) {
        this.username = username;
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }
}
