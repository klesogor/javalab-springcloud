package it61.springlabs.ticketservice.Domain.Comments.UseCases;

import it61.springlabs.data.dto.ticket.CommentWriteDTO;
import it61.springlabs.ticketservice.Domain.Comments.Comment;
import it61.springlabs.ticketservice.Domain.Tickets.Ticket;
import it61.springlabs.ticketservice.Domain.Comments.dal.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentTicket {
    private CommentRepository repository;

    public CommentTicket(@Autowired CommentRepository repository) {
        this.repository = repository;
    }

    public void Comment(CommentWriteDTO dto, Ticket ticket){
        Comment comment = new Comment(dto.getText(), dto.getFromId(), ticket.getId());
        repository.save(comment);
    }
}
