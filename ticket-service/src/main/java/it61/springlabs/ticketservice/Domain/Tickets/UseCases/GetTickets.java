package it61.springlabs.ticketservice.Domain.Tickets.UseCases;

import it61.springlabs.data.dto.TicketReadDto;
import it61.springlabs.data.entities.Ticket;
import it61.springlabs.ticketservice.Domain.Comments.Repository.Contracts.CommentRepository;
import it61.springlabs.ticketservice.Domain.Tickets.Repository.Contracts.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class GetTickets {
    private TicketRepository ticketRepository;

    public GetTickets(@Autowired TicketRepository repository, @Autowired CommentRepository commentRepository){
        this.ticketRepository = repository;
    }

    public Iterable<TicketReadDto> getTicketsForVps(UUID vpsId){
        return StreamSupport.stream(ticketRepository.getTicketsByVpsId(vpsId).spliterator(), false)
                .map((Ticket ticket)-> ticket.ToDto())
                .collect(Collectors.toList());
    }
}
