package it61.springlabs.queueConsumer.dal;

import it61.springlabs.queueConsumer.entities.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LogRepository extends CrudRepository<Log, UUID> {
}
