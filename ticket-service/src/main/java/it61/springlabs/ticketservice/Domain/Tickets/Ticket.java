package it61.springlabs.ticketservice.Domain.Tickets;

import it61.springlabs.ticketservice.Domain.Comments.Comment;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Where(clause = "closed_at is NULL")
public final class Ticket {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type = "uuid-char")
    private UUID id;
    @Type(type = "uuid-char")
    private UUID userId;
    @Type(type = "uuid-char")
    private UUID vpsId;
    private String description;
    private Date openedAt;
    @Column(name = "closed_at")
    private Date closedAt;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    private List<Comment> comments;

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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
