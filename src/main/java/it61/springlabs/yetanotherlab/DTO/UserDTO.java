package it61.springlabs.yetanotherlab.DTO;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public final class UserDTO {
    @NotNull
    private String username;
    @NotNull
    private String description;
    private UUID[] vps;

    public UserDTO(String username, String description, UUID[] vps) {
        this.username = username;
        this.description = description;
        this.vps = vps;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public UUID[] getVps() {
        return vps;
    }
}
