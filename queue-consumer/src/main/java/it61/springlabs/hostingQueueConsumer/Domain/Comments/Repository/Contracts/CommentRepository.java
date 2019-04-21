package it61.springlabs.hostingQueueConsumer.Domain.Comments.Repository.Contracts;

import it61.springlabs.hostingQueueConsumer.Domain.Comments.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {
    Iterable<Comment> getCommentsByTicketId(UUID ticketId);
}
