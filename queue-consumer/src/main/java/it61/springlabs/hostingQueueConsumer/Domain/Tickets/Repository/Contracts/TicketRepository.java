package it61.springlabs.hostingQueueConsumer.Domain.Tickets.Repository.Contracts;

import it61.springlabs.hostingQueueConsumer.Domain.Tickets.Ticket;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TicketRepository extends CrudRepository<Ticket, UUID> {
    Iterable<Ticket> getTicketsByVpsId(UUID vpsId);
}
