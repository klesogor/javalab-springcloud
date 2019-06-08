package it61.springlabs.ticketservice.Domain.Tickets.dal;

import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {
    Iterable<Ticket> getTicketsByVpsId(UUID vpsId);
    Iterable<Ticket> getTicketsByUserId(UUID userId);
}
