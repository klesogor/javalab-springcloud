package it61.springlabs.yetanotherlab.DTO;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class VpsDTO {
    @NotNull
    private String operatingSystem;
    private UUID owner_id;

    public VpsDTO(String operatingSystem, UUID owner) {
        this.operatingSystem = operatingSystem;
        this.owner_id = owner;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public UUID getOwner() {
        return owner_id;
    }
}
