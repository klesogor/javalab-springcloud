package it61.springlabs.hostingQueueConsumer.Controllers;

import it61.springlabs.hostingQueueConsumer.Exceptions.DomainException;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface CRUDControllerInterface<OUTDTO,INDTO> {
    Iterable<OUTDTO> getAll(Integer page,Integer perPage) throws DomainException;
    OUTDTO findById(UUID id) throws DomainException;
    OUTDTO create(INDTO dto, BindingResult binding) throws DomainException;
    OUTDTO update( UUID id ,INDTO dto, BindingResult binding) throws DomainException;
    void delete(UUID id) throws DomainException;
}
