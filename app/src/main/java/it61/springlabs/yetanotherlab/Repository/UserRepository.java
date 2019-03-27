package it61.springlabs.yetanotherlab.Repository;

import it61.springlabs.yetanotherlab.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
