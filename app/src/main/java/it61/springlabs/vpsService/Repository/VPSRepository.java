package it61.springlabs.vpsService.Repository;

import it61.springlabs.data.entities.Vps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VPSRepository extends JpaRepository<Vps, UUID> {
}
