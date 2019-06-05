package it61.springlabs.eurekaclient.dal;

import it61.springlabs.eurekaclient.entities.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails, UUID> {
}
