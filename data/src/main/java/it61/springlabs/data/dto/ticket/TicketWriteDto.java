package it61.springlabs.data.dto.ticket;

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

    public TicketWriteDto() {
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

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setVpsId(UUID vpsId) {
        this.vpsId = vpsId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOpenedAt(Date openedAt) {
        this.openedAt = openedAt;
    }
}
