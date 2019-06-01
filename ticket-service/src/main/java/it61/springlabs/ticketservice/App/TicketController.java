package it61.springlabs.ticketservice.App;

import it61.springlabs.data.dto.ticket.TicketWriteDto;
import it61.springlabs.data.entities.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.UseCases.CloseTicket;
import it61.springlabs.ticketservice.Domain.Tickets.UseCases.CreateTicket;
import it61.springlabs.ticketservice.Domain.Tickets.UseCases.GetTicketsForVps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
public class TicketController {
    @GetMapping(value="/api/ticket/{vpsId}")
    public Iterable<Ticket> GetActiveTicketsForVps
            (
                    @PathVariable(value = "vpsId") UUID vpsId,
                    @Autowired GetTicketsForVps usecase
            )
    {
        return usecase.handle(vpsId);
    }

    @PostMapping(value="/api/ticket")
    public Ticket CreateTicket(TicketWriteDto dto, @Autowired CreateTicket usecase)
    {
        return usecase.createTicket(dto);
    }

    @DeleteMapping(value = "/api/ticket/{id}")
    public void CloseTicket(@PathVariable(value = "id") UUID ticketId, @Autowired CloseTicket usecase){
        usecase.closeTicket(ticketId, new Date());
    }
}
