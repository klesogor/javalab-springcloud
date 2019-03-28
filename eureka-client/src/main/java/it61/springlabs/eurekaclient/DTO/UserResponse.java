package it61.springlabs.eurekaclient.DTO;

import java.util.UUID;

public final class UserResponse {
    private UUID id;
    private String username;
    private String description;
    private String phone;
    private String secret;
    private Iterable<VPSResponse> vps;

    public UserResponse
            (
                UUID id,
                String username,
                String descroption,
                String phone,
                String secret,
                Iterable<VPSResponse> vps
            )
    {
        this.id = id;
        this.username = username;
        this.description = descroption;
        this.vps = vps;
        this.phone = phone;
        this.secret = secret;
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

    public String getSecret() {
        return secret;
    }

    public String getPhone() {
        return phone;
    }
}