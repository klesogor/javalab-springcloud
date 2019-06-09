package it61.springlabs.data.dto.ticket;

import java.util.UUID;

public class CommentWriteDTO {
    private String text;
    private UUID fromId;
    private UUID ticketId;

    public CommentWriteDTO(String text, UUID fromId, UUID ticketId) {
        this.text = text;
        this.fromId = fromId;
        this.ticketId = ticketId;
    }

    public String getText() {
        return text;
    }

    public UUID getFromId() {
        return fromId;
    }

    public UUID getTicketId() {
        return ticketId;
    }
}
