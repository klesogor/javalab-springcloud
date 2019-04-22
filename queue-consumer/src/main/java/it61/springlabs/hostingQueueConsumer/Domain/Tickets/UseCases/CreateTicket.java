package it61.springlabs.hostingQueueConsumer.Domain.Tickets.UseCases;

import it61.springlabs.data.TicketWriteDto;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.Repository.Contracts.TicketRepository;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                dto.getOpenedAt()
        );
        repository.save(ticket);

        return ticket;
    }
}
