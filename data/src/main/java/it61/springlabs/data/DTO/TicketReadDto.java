package it61.springlabs.data.DTO;

import java.util.Date;
import java.util.UUID;

public final class TicketReadDto {
    private UUID id;
    private UUID userId;
    private UUID vpsId;
    private String description;
    private Date openedAt;
    private Date closedAt;
    private Iterable<CommentDTO> comments;

    public TicketReadDto(UUID userId, UUID vpsId, String description, Date openedAt, Date closedAt, Iterable<CommentDTO> comments) {
        this.userId = userId;
        this.vpsId = vpsId;
        this.description = description;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
        this.comments = comments;
    }

    public TicketReadDto() {
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
    }

    public Iterable<CommentDTO> getComments() {
        return comments;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public void setComments(Iterable<CommentDTO> comments) {
        this.comments = comments;
    }
}
