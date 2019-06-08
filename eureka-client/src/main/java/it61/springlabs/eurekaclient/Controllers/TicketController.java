package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.ticket.CommentWriteDTO;
import it61.springlabs.data.dto.ticket.TicketReadDto;
import it61.springlabs.data.dto.ticket.TicketWriteDto;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public class TicketController {
    private TicketService service;

    @Autowired
    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping(value = "/api/v1/ticket/forVps/{vpsId}")
    Response<Iterable<TicketReadDto>> GetActiveTicketsForVps(@PathVariable(value = "vpsId") UUID vpsId){
        return service.GetActiveTicketsForVps(vpsId);
    }

    @GetMapping(value = "/api/v1/ticket/byUser/{userId}")
    Response<Iterable<TicketReadDto>> GetActiveTicketsForUser(@PathVariable(value = "userId") UUID userId){
        return  service.GetActiveTicketsForUser(userId);
    }

    @GetMapping(value = "/api/v1/ticket")
    Response<Iterable<TicketReadDto>> GetTickets(){
        return  service.GetTickets();
    }

    @GetMapping(value = "/api/v1/ticket/{id}")
    Response<TicketReadDto> GetTicket(@PathVariable(value = "id") UUID ticketId){
        return service.GetTicket(ticketId);
    }

    @PostMapping(value = "/api/v1/ticket")
    Response<TicketReadDto> CreateTicket(@RequestBody TicketWriteDto dto){
        return service.CreateTicket(dto);
    }

    @PutMapping(value = "/api/v1/ticket/{id}/comment")
    Response<TicketReadDto> CommentTicket(@PathVariable(name = "id") UUID id,@RequestBody CommentWriteDTO comment){
        return service.CommentTicket(id,comment);
    }

    @DeleteMapping(value = "/api/v1/ticket/{id}")
    void CloseTicket(@PathVariable(value = "id") UUID ticketId){
        service.CloseTicket(ticketId);
    }
}
