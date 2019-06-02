package it61.springlabs.ticketservice.Domain.Comments.dal;

import it61.springlabs.ticketservice.Domain.Comments.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {
    Iterable<Comment> getCommentsByTicketId(UUID ticketId);
}
