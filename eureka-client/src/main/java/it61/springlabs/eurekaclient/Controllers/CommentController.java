package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.dto.ticket.CommentReadDto;
import it61.springlabs.data.dto.ticket.CommentWriteDTO;
import it61.springlabs.data.dto.ticket.TicketCloseDTO;
import it61.springlabs.data.exceptions.AuthException;
import it61.springlabs.data.generic.Response;
import it61.springlabs.eurekaclient.Auth.JwtTokenDetails;
import it61.springlabs.eurekaclient.Services.Logger;
import it61.springlabs.eurekaclient.Services.TicketService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class CommentController {
    private TicketService service;
    private Logger logger;

    @Autowired
    public CommentController(TicketService service, Logger logger) {
        this.service = service;
        this.logger = logger;
    }


    @MessageMapping("/comment")
    @SendTo("/topic/comments")
    public Response<CommentReadDto> CommentTicket(CommentWriteDTO comment){
        logger.info("Adding comment to ticket " + comment.getTicketId());
        return service.CommentTicket(comment);
    }

    @MessageMapping("/ticket/close")
    @SendTo("/topic/ticket/closed")
    public UUID CloseTicket(TicketCloseDTO dto){
        logger.info("Closing ticket " + dto.getTicketId());
        service.CloseTicket(dto.getTicketId());
        return dto.getTicketId();
    }
}
