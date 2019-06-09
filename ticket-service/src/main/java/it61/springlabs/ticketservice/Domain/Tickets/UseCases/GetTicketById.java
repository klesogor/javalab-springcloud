package it61.springlabs.ticketservice.Domain.Tickets.UseCases;

import it61.springlabs.data.exceptions.NotFoundException;
import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.dal.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetTicketById {
    private TicketRepository ticketRepository;

    @Autowired
    public GetTicketById(TicketRepository repository){
        this.ticketRepository = repository;
    }

    public Ticket getTicket(UUID id){
        return ticketRepository.findById(id).orElseThrow(()-> NotFoundException.of(id, "Ticket"));
    }
}
