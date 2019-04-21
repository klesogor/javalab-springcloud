package it61.springlabs.eurekaclient.DTO;

import java.util.UUID;

public class Comment {
    private UUID id;
    private String text;
    private UUID fromId;
    private UUID ticketId;

    public Comment(String text, UUID from, UUID ticketId) {
        this.id = UUID.randomUUID();
        this.text = text;
        this.fromId = from;
        this.ticketId = ticketId;
    }

    public Comment(){}

    public String getText() {
        return text;
    }

    public Comment setText(String text) {
        this.text = text;
        return this;
    }

    public UUID getFromId() {
        return fromId;
    }

    public Comment setFrom(UUID from) {
        this.fromId = from;
        return this;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public Comment setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    public UUID getId() {
        return id;
    }
}
