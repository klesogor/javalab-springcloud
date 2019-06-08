package it61.springlabs.data.dto.ticket;

import java.util.Date;
import java.util.UUID;

public class TicketReadDto {
    private UUID id;
    private UUID userId;
    private UUID vpsId;
    private String description;
    private Date openedAt;

    private Iterable<CommentReadDto> comments;

    public TicketReadDto(UUID id, UUID userId, UUID vpsId, String description, Date openedAt, Iterable<CommentReadDto> comments) {
        this.id = id;
        this.userId = userId;
        this.vpsId = vpsId;
        this.description = description;
        this.openedAt = openedAt;
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

    public Iterable<CommentReadDto> getComments() {
        return comments;
    }
}
