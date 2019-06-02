package it61.springlabs.ticketservice.Domain.Tickets.UseCases;

import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.dal.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

public class GetTicketsForVps {
    private TicketRepository repository;

    public GetTicketsForVps(@Autowired TicketRepository repository) {
        this.repository = repository;
    }

    public Iterable<Ticket> handle(UUID vpsId){
        return this.repository.getTicketsByVpsId(vpsId);
    }
}
