package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.ticket.CommentWriteDTO;
import it61.springlabs.data.dto.ticket.TicketReadDto;
import it61.springlabs.data.dto.ticket.TicketWriteDto;
import it61.springlabs.data.exceptions.AuthException;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.Auth.JwtTokenDetails;
import it61.springlabs.eurekaclient.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
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

    @GetMapping(value = "/api/v1/ticket/me")
    Response<Iterable<TicketReadDto>> GetActiveTicketsForUser(){
        JwtTokenDetails details = (JwtTokenDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return  service.GetActiveTicketsForUser(details.getUserId());
    }

    @GetMapping(value = "/api/v1/ticket")
    Response<Iterable<TicketReadDto>> GetTickets(){
        if(!isAuthorized()) throw AuthException.of("Unauthorized!");
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

    @DeleteMapping(value = "/api/v1/ticket/{id}")
    void CloseTicket(@PathVariable(value = "id") UUID ticketId){
        if(!isAuthorized()) throw AuthException.of("Unauthorized!");
        service.CloseTicket(ticketId);
    }

    private boolean isAuthorized(){
        return ((JwtTokenDetails)SecurityContextHolder.getContext().getAuthentication().getDetails()).isAdmin();
    }
}
