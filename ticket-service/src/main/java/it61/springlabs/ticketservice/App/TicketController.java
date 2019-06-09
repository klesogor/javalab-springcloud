package it61.springlabs.ticketservice.App;

import it61.springlabs.data.dto.ticket.CommentReadDto;
import it61.springlabs.data.dto.ticket.CommentWriteDTO;
import it61.springlabs.data.dto.ticket.TicketReadDto;
import it61.springlabs.data.dto.ticket.TicketWriteDto;
import it61.springlabs.data.generic.Response;
import it61.springlabs.ticketservice.Domain.Comments.Comment;
import it61.springlabs.ticketservice.Domain.Comments.UseCases.CommentTicket;
import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Tickets.UseCases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class TicketController {
    private CloseTicket closeTicket;
    private CommentTicket commentTicket;
    private CreateTicket createTicket;
    private GetTicketById ticketById;
    private GetTickets getTickets;
    private GetUserTickets getUserTickets;
    private GetTicketsForVps getTicketsForVps;

    @Autowired
    public TicketController(CloseTicket closeTicket, CommentTicket commentTicket, CreateTicket createTicket, GetTicketById ticketById, GetTickets getTickets, GetUserTickets getUserTickets, GetTicketsForVps getTicketsForVps) {
        this.closeTicket = closeTicket;
        this.commentTicket = commentTicket;
        this.createTicket = createTicket;
        this.ticketById = ticketById;
        this.getTickets = getTickets;
        this.getUserTickets = getUserTickets;
        this.getTicketsForVps = getTicketsForVps;
    }

    @GetMapping(value = "/api/v1/ticket/forVps/{vpsId}")
    public Response<Iterable<TicketReadDto>> GetActiveTicketsForVps(@PathVariable(value = "vpsId") UUID vpsId) {
        return Response.Of(
                StreamSupport.stream(getTicketsForVps.handle(vpsId).spliterator(), false)
                        .map(t -> ticketToDto(t, new ArrayList<>()))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/api/v1/ticket/byUser/{userId}")
    public Response<Iterable<TicketReadDto>> GetActiveTicketsForUser(@PathVariable(value = "userId") UUID userId) {
        return Response.Of(
                StreamSupport.stream(getUserTickets.handle(userId).spliterator(), false)
                        .map(t -> ticketToDto(t, new ArrayList<>()))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/api/v1/ticket")
    public Response<Iterable<TicketReadDto>> GetTickets() {
        return Response.Of(
                StreamSupport.stream(getTickets.getTickets().spliterator(), false)
                        .map(t -> ticketToDto(t, new ArrayList<>()))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/api/v1/ticket/{id}")
    public Response<TicketReadDto> GetTicket(@PathVariable(value = "id") UUID ticketId) {
        Ticket ticket = ticketById.getTicket(ticketId);
        return Response.Of(ticketToDto(ticket, ticket.getComments()));
    }

    @PostMapping(value = "/api/v1/ticket")
    public Response<TicketReadDto> CreateTicket(@RequestBody TicketWriteDto dto) {
        return Response.Of(ticketToDto(createTicket.createTicket(dto), new ArrayList<>()));
    }

    @PutMapping(value = "/api/v1/ticket/comment")
    public Response<TicketReadDto> CommentTicket
            (
                    @RequestBody CommentWriteDTO comment
            ) {
        Ticket res = commentTicket.Comment(comment, comment.getTicketId());
        return Response.Of(ticketToDto(res, res.getComments()));
    }

    @DeleteMapping(value = "/api/v1/ticket/{id}")
    public void CloseTicket(@PathVariable(value = "id") UUID ticketId) {
        closeTicket.closeTicket(ticketId, new Date());
    }

    private TicketReadDto ticketToDto(Ticket ticket, Iterable<Comment> comments) {
        Iterable<CommentReadDto> comm = StreamSupport.stream(comments.spliterator(), false)
                .map(c -> new CommentReadDto(c.getId(), c.getText(), c.getFromId()))
                .collect(Collectors.toList());
        return new TicketReadDto(
                ticket.getId(),
                ticket.getUserId(),
                ticket.getVpsId(),
                ticket.getDescription(),
                ticket.getOpenedAt(),
                comm
        );
    }
}
