package it61.springlabs.hostingQueueConsumer.Domain.Comments.UseCases;

import DTO.CommentWriteDTO;
import it61.springlabs.hostingQueueConsumer.Domain.Comments.Comment;
import it61.springlabs.hostingQueueConsumer.Domain.Comments.Repository.Contracts.CommentRepository;
import it61.springlabs.hostingQueueConsumer.Domain.Tickets.Ticket;
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
