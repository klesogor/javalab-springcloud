package it61.springlabs.data.dto.ticket;

import java.util.UUID;

public class TicketCloseDTO {
    private UUID ticketId;

    public TicketCloseDTO(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public TicketCloseDTO() {
    }

    public UUID getTicketId() {
        return ticketId;
    }
}
