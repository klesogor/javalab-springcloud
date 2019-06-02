package it61.springlabs.vpsService.Controllers;

import it61.springlabs.data.exceptions.DomainException;
import it61.springlabs.data.generic.Response;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface CRUDControllerInterface<OUTDTO,INDTO> {
    Response<Iterable<OUTDTO>> getAll(Integer page, Integer perPage) throws DomainException;
    Response<OUTDTO> findById(UUID id) throws DomainException;
    Response<OUTDTO> create(INDTO dto, BindingResult binding) throws DomainException;
    Response<OUTDTO> update( UUID id ,INDTO dto, BindingResult binding) throws DomainException;
    void delete(UUID id) throws DomainException;
}
