package it61.springlabs.ticketservice.Domain.Tickets.UseCases;

import it61.springlabs.data.dto.ticket.TicketWriteDto;
import it61.springlabs.data.entities.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.Repository.Contracts.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CreateTicket {
    private TicketRepository repository;
    public CreateTicket(@Autowired TicketRepository repository){
        this.repository = repository;
    }

    public Ticket createTicket(TicketWriteDto dto)
    {
        Ticket ticket = new Ticket(
                dto.getUserId(),
                dto.getVpsId(),
                dto.getDescription(),
                new Date()
        );
        repository.save(ticket);

        return ticket;
    }
}
