package it61.springlabs.eurekaclient.DTO;

import java.util.Date;
import java.util.UUID;

public final class TicketReadDto {
    private UUID id;
    private UUID userId;
    private UUID vpsId;
    private String description;
    private Date openedAt;
    private Date closedAt;
    private Iterable<Comment> comments;

    public TicketReadDto(UUID userId, UUID vpsId, String description, Date openedAt, Date closedAt, Iterable<Comment> comments) {
        this.userId = userId;
        this.vpsId = vpsId;
        this.description = description;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.comments = comments;
    }

    public UUID getId() {
        return id;
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

    public Date getClosedAt() {
        return closedAt;
    }}
