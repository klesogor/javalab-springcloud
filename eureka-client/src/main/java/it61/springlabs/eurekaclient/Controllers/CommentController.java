package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.ticket.CommentWriteDTO;
import it61.springlabs.data.dto.ticket.TicketReadDto;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class CommentController {
    private TicketService service;

    @Autowired
    public CommentController(TicketService service) {
        this.service = service;
    }

    @MessageMapping("/comment")
    @SendTo("/topic/comments")
    Response<TicketReadDto> CommentTicket(CommentWriteDTO comment){
        return service.CommentTicket(comment);
    }
}
