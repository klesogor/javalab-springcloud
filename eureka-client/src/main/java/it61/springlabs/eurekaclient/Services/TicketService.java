package it61.springlabs.eurekaclient.Services;

import feign.Headers;
import it61.springlabs.data.dto.ticket.CommentWriteDTO;
import it61.springlabs.data.dto.ticket.TicketReadDto;
import it61.springlabs.data.dto.ticket.TicketWriteDto;
import it61.springlabs.data.generic.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@FeignClient("ticket-service")
public interface TicketService {
    @GetMapping(value = "/api/v1/ticket/forVps/{vpsId}")
    @Headers("Content-Type: application/json")
    Response<Iterable<TicketReadDto>> GetActiveTicketsForVps(@PathVariable(value = "vpsId") UUID vpsId);

    @GetMapping(value = "/api/v1/ticket/byUser/{userId}")
    @Headers("Content-Type: application/json")
    Response<Iterable<TicketReadDto>> GetActiveTicketsForUser(@PathVariable(value = "userId") UUID userId);

    @GetMapping(value = "/api/v1/ticket")
    @Headers("Content-Type: application/json")
    Response<Iterable<TicketReadDto>> GetTickets();

    @GetMapping(value = "/api/v1/ticket/{id}")
    @Headers("Content-Type: application/json")
    Response<TicketReadDto> GetTicket(@PathVariable(value = "id") UUID ticketId);

    @PostMapping(value = "/api/v1/ticket")
    @Headers("Content-Type: application/json")
    Response<TicketReadDto> CreateTicket(@RequestBody TicketWriteDto dto);

    @PutMapping(value = "/api/v1/ticket/{id}/comment")
    @Headers("Content-Type: application/json")
    Response<TicketReadDto> CommentTicket(@PathVariable(name = "id") UUID id,@RequestBody CommentWriteDTO comment);

    @DeleteMapping(value = "/api/v1/ticket/{id}")
    @Headers("Content-Type: application/json")
    void CloseTicket(@PathVariable(value = "id") UUID ticketId);
}
