package it61.springlabs.hostingQueueConsumer.Repository;

import it61.springlabs.hostingQueueConsumer.Models.Vps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VPSRepository extends JpaRepository<Vps, UUID> {
}
