package it61.springlabs.hostingQueueConsumer.Repository;

import Entities.Vps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VPSRepository extends JpaRepository<Vps, UUID> {
}
