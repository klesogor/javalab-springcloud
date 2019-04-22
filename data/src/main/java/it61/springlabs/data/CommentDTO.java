package it61.springlabs.data;

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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFromId(UUID fromId) {
        this.fromId = fromId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }
}
