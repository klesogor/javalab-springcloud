package it61.springlabs.hostingQueueConsumer.Repository;

import it61.springlabs.hostingQueueConsumer.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
