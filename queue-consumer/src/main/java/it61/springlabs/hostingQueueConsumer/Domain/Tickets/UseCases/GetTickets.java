package it61.springlabs.hostingQueueConsumer.Domain.Tickets.UseCases;

import it61.springlabs.data.DTO.TicketReadDto;
import it61.springlabs.hostingQueueConsumer.Domain.Comments.Repository.Contracts.CommentRepository;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.Repository.Contracts.TicketRepository;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class GetTickets {
    private TicketRepository ticketRepository;
    private CommentRepository commentRepository;

    public GetTickets(@Autowired TicketRepository repository, @Autowired CommentRepository commentRepository){
        this.ticketRepository = repository;
        this.commentRepository = commentRepository;
    }

    public Iterable<TicketReadDto> getTicketsForVps(UUID vpsId){
        return StreamSupport.stream(ticketRepository.getTicketsByVpsId(vpsId).spliterator(), false)
                .map((Ticket ticket)-> ticket.ToDto())
                .collect(Collectors.toList());
    }
}
