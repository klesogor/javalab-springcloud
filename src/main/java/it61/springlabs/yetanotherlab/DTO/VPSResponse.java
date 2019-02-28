package it61.springlabs.yetanotherlab.DTO;

import java.util.UUID;

public final class VPSResponse {
    private String operatingSystem;
    private UUID id;

    public VPSResponse(UUID id,String operatingSystem) {
        this.id = id;
        this.operatingSystem = operatingSystem;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public VPSResponse(UUID id) {
        this.id = id;
    }
}
