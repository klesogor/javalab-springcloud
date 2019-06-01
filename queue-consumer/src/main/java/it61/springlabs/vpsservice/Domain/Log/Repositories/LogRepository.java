package it61.springlabs.vpsservice.Domain.Log.Repositories;

import it61.springlabs.vpsservice.Domain.Log.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LogRepository extends CrudRepository<Log, UUID> {
}
