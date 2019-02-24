package it61.springlabs.yetanotherlab.Repository;

import it61.springlabs.yetanotherlab.Models.Vps;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VPSRepository extends JpaRepository<Vps, UUID> {
}
