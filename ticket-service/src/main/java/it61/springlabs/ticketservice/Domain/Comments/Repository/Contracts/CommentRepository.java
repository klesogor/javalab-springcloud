package it61.springlabs.ticketservice.Domain.Comments.Repository.Contracts;

import it61.springlabs.data.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {
    Iterable<Comment> getCommentsByTicketId(UUID ticketId);
}
