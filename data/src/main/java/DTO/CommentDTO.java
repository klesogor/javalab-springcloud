package DTO;

import java.util.UUID;

public class CommentDTO {
    private UUID id;
    private String text;
    private UUID fromId;
    private UUID ticketId;

public CommentDTO(String text, UUID from, UUID ticketId) {
        this.id = UUID.randomUUID();
        this.text = text;
        this.fromId = from;
        this.ticketId = ticketId;
    }

    public CommentDTO(){}

    public String getText() {
        return text;
    }

    public UUID getFromId() {
        return fromId;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public UUID getId() {
        return id;
    }
}
