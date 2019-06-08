package it61.springlabs.ticketservice.Domain.Tickets.UseCases;

import it61.springlabs.data.exceptions.NotFoundException;
import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.dal.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class GetTicketById {
    private TicketRepository ticketRepository;

    public GetTicketById(@Autowired TicketRepository repository){
        this.ticketRepository = repository;
    }

    public Ticket getTicket(UUID id){
        return ticketRepository.findById(id).orElseThrow(()-> NotFoundException.of(id, "Ticket"));
    }
}
