package it61.springlabs.ticketservice.Domain.Tickets.UseCases;

import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.dal.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetTicketsForVps {
    private TicketRepository repository;

    @Autowired
    public GetTicketsForVps(TicketRepository repository) {
        this.repository = repository;
    }

    public Iterable<Ticket> handle(UUID vpsId){
        return this.repository.getTicketsByVpsId(vpsId);
    }
}
