package it61.springlabs.eurekaclient.DTO;

import java.util.Date;
import java.util.UUID;

public final class TicketWriteDto {
    private UUID userId;
    private UUID vpsId;
    private String description;
    private Date openedAt;

    public TicketWriteDto(UUID userId, UUID vpsId, String description, Date openedAt) {
        this.userId = userId;
        this.vpsId = vpsId;
        this.description = description;
        this.openedAt = openedAt == null ? new Date() : openedAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getVpsId() {
        return vpsId;
    }

    public String getDescription() {
        return description;
    }

    public Date getOpenedAt() {
        return openedAt;
    }
}
