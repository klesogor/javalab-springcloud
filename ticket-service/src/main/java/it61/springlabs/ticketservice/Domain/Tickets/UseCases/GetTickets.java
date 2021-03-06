package it61.springlabs.ticketservice.Domain.Tickets.UseCases;

import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.dal.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetTickets {
    private TicketRepository ticketRepository;

    @Autowired
    public GetTickets(TicketRepository repository){
        this.ticketRepository = repository;
    }

    public Iterable<Ticket> getTickets(){
        return ticketRepository.findAll();
    }
}
