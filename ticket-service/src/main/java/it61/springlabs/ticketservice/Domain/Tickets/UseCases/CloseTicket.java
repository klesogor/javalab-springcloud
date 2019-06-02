package it61.springlabs.ticketservice.Domain.Tickets.UseCases;

import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.dal.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class CloseTicket {
    private TicketRepository repository;
    public CloseTicket(@Autowired TicketRepository repository){
        this.repository = repository;
    }

    public boolean closeTicket(UUID ticketId, Date closedAt){
        Ticket ticket = repository.findById(ticketId).orElseGet(()->null);
        if(ticket == null) return false;
        ticket.Close(closedAt);
        repository.save(ticket);
        return true;
    }
}
