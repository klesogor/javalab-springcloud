package it61.springlabs.data.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;
    private String text;
    private UUID fromId;
    @ManyToOne(targetEntity = Ticket.class)
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
