package it61.springlabs.hostingQueueConsumer.Domain.Tickets.DTO;

import it61.springlabs.hostingQueueConsumer.Domain.Comments.Comment;
import it61.springlabs.hostingQueueConsumer.Domain.Comments.Repository.Contracts.CommentRepository;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.Ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public final class TicketReadDto {
    private UUID id;
    private UUID userId;
    private UUID vpsId;
    private String description;
    private Date openedAt;
    private Date closedAt;
    private Iterable<Comment> comments;

    public TicketReadDto(UUID userId, UUID vpsId, String description, Date openedAt, Date closedAt, Iterable<Comment> comments) {
        this.userId = userId;
        this.vpsId = vpsId;
        this.description = description;
        this.openedAt = openedAt;
        this.closedAt = closedAt;
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

    public Date getClosedAt() {
        return closedAt;
    }

    public static TicketReadDto Of(Ticket ticket, CommentRepository commentRepository){
        return new TicketReadDto(
                ticket.getId(),
                ticket.getVpsId(),
                ticket.getDescription(),
                ticket.getOpenedAt(),
                ticket.getClosedAt(),
                commentRepository.getCommentsByTicketId(ticket.getId())
        );
    }
    public static TicketReadDto Of(Ticket ticket){
        return new TicketReadDto(
                ticket.getId(),
                ticket.getVpsId(),
                ticket.getDescription(),
                ticket.getOpenedAt(),
                ticket.getClosedAt(),
                new ArrayList<>()
        );
    }
}
