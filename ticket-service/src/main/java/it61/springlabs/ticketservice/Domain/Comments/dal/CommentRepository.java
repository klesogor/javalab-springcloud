package it61.springlabs.ticketservice.Domain.Comments.dal;

import it61.springlabs.ticketservice.Domain.Comments.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
