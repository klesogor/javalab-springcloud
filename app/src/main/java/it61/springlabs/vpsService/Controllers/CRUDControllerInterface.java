package it61.springlabs.vpsService.Controllers;

import it61.springlabs.data.exceptions.DomainException;
import it61.springlabs.data.generic.Response;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface CRUDControllerInterface<T,INDTO> {
    Response<Iterable<T>> getAll(Integer page, Integer perPage) throws DomainException;
    Response<T> findById(UUID id) throws DomainException;
    Response<T> create(INDTO dto, BindingResult binding) throws DomainException;
    Response<T> update( UUID id ,INDTO dto, BindingResult binding) throws DomainException;
    void delete(UUID id) throws DomainException;
}
