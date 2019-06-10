package it61.springlabs.ticketservice.Domain.Comments.UseCases;

import it61.springlabs.data.dto.ticket.CommentWriteDTO;
import it61.springlabs.data.exceptions.NotFoundException;
import it61.springlabs.ticketservice.Domain.Comments.Comment;
import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Comments.dal.CommentRepository;
import it61.springlabs.ticketservice.Domain.Tickets.UseCases.GetTicketById;
import it61.springlabs.ticketservice.Domain.Tickets.UseCases.GetTickets;
import it61.springlabs.ticketservice.Domain.Tickets.dal.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentTicket {
    private CommentRepository repository;
    private TicketRepository ticketRepository;
    private GetTicketById getTicketUsecase;

    @Autowired
    public CommentTicket(CommentRepository repository, TicketRepository ticketRepository, GetTicketById usecase) {
        this.repository = repository;
        this.ticketRepository = ticketRepository;
        this.getTicketUsecase = usecase;
    }

    public Comment Comment(CommentWriteDTO dto, UUID ticketId){
        Ticket ticket = getTicketUsecase.getTicket(ticketId);
        Comment comment = new Comment(dto.getText(), dto.getFromId());
        repository.save(comment);
        ticket.getComments().add(comment);
        ticketRepository.save(ticket);

        return comment;
    }
}
