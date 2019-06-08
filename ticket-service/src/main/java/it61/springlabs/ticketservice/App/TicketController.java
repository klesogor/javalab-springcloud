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
    @GetMapping(value="/api/v1/ticket/forVps/{vpsId}")
    public Response<Iterable<TicketReadDto>> GetActiveTicketsForVps
            (
                    @PathVariable(value = "vpsId") UUID vpsId,
                    @Autowired GetTicketsForVps usecase
            )
    {
        return Response.Of(
                StreamSupport.stream(usecase.handle(vpsId).spliterator(),false)
                .map(t ->  ticketToDto(t, new ArrayList<>()))
                .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/api/v1/ticket/byUser/{userId}")
    public Response<Iterable<TicketReadDto>> GetActiveTicketsForUser
            (
                    @PathVariable(value = "userId") UUID userId,
                    @Autowired GetUserTickets usecase
            )
    {
        return Response.Of(
                StreamSupport.stream(usecase.handle(userId).spliterator(),false)
                        .map(t ->  ticketToDto(t, new ArrayList<>()))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/api/v1/ticket")
    public Response<Iterable<TicketReadDto>> GetTickets(@Autowired GetTickets usecase){
        return Response.Of(
                StreamSupport.stream(usecase.getTickets().spliterator(),false)
                        .map(t ->  ticketToDto(t, new ArrayList<>()))
                        .collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/api/v1/ticket/{id}")
    public Response<TicketReadDto> GetTicket(@PathVariable(value = "id") UUID ticketId, @Autowired GetTicketById usecase){
        Ticket ticket = usecase.getTicket(ticketId);
        return Response.Of(ticketToDto(ticket,ticket.getComments()));
    }

    @PostMapping(value="/api/v1/ticket")
    public Response<TicketReadDto> CreateTicket(@RequestBody TicketWriteDto dto, @Autowired CreateTicket usecase) {
        return Response.Of(ticketToDto(usecase.createTicket(dto), new ArrayList<>()));
    }

    @PutMapping(value = "/api/v1/ticket/{id}/comment")
    public Response<TicketReadDto> CommentTicket
            (
                    @PathVariable(name = "id") UUID id,
                    @RequestBody CommentWriteDTO comment,
                    @Autowired CommentTicket usecase
            )
    {
        Ticket res = usecase.Comment(comment,id);
        return  Response.Of(ticketToDto(res,res.getComments()));
    }

    @DeleteMapping(value = "/api/v1/ticket/{id}")
    public void CloseTicket(@PathVariable(value = "id") UUID ticketId, @Autowired CloseTicket usecase){
        usecase.closeTicket(ticketId, new Date());
    }

    private TicketReadDto ticketToDto(Ticket ticket, Iterable<Comment> comments){
        Iterable<CommentReadDto> comm = StreamSupport.stream(comments.spliterator(),false)
                .map(c -> new CommentReadDto(c.getId(),c.getText(),c.getFromId()))
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
