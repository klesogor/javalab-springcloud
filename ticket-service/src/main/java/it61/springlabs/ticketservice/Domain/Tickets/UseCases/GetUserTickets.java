package it61.springlabs.ticketservice.Domain.Tickets.UseCases;

import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.dal.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetUserTickets {
    private TicketRepository repository;

    @Autowired
    public GetUserTickets(TicketRepository repository) {
        this.repository = repository;
    }

    public Iterable<Ticket> handle(UUID userId){
        return repository.getTicketsByUserId(userId);
    }
}
