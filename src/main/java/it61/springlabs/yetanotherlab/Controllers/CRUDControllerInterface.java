package it61.springlabs.yetanotherlab.Controllers;

import it61.springlabs.yetanotherlab.Exceptions.DomainException;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface CRUDControllerInterface<T,TDTO> {
    Iterable<T> getAll(Integer page,Integer perPage) throws DomainException;
    T findById(UUID id) throws DomainException;
    T create(TDTO dto, BindingResult binding) throws DomainException;
    T update( UUID id ,TDTO dto, BindingResult binding) throws DomainException;
    void delete(UUID id) throws DomainException;
}
