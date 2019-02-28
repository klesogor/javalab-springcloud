package it61.springlabs.yetanotherlab.DTO;

import java.util.UUID;

public final class UserResponse {
    private UUID id;
    private String username;
    private String description;
    private Iterable<VPSResponse> vps;

    public UserResponse(UUID id,String username, String descroption, Iterable<VPSResponse> vps) {
        this.id = id;
        this.username = username;
        this.description = descroption;
        this.vps = vps;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public Iterable<VPSResponse> getVps() {
        return vps;
    }

    public UUID getId() {
        return id;
    }
}