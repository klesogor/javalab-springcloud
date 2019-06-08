package it61.springlabs.ticketservice.Domain.Tickets;

import it61.springlabs.ticketservice.Domain.Comments.Comment;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Where(clause = "closedat = null")
public final class Ticket {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;
    private UUID userId;
    private UUID vpsId;
    private String description;
    private Date openedAt;
    private Date closedAt;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    private ArrayList<Comment> comments;

    public Ticket(UUID userId, UUID vpsId, String description, Date openedAt) {
        id = UUID.randomUUID();
        this.userId = userId;
        this.vpsId = vpsId;
        this.description = description;
        this.openedAt = openedAt;
    }

    protected Ticket(){}

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

    public void Close(Date closedAt) {
        this.closedAt = closedAt;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
