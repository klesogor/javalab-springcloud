package it61.springlabs.vpsService.dal;

import it61.springlabs.vpsService.entities.Vps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VPSRepository extends JpaRepository<Vps, UUID> {
    Iterable<Vps> findAllByUserId(UUID userId);
}
