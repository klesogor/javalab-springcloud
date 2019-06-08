package it61.springlabs.ticketservice.Domain.Comments;

import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
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

    public Comment(String text, UUID from) {
        this.id = UUID.randomUUID();
        this.text = text;
        this.fromId = from;
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

    public UUID getId() {
        return id;
    }
}
