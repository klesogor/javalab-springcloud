package it61.springlabs.vpsService.Services.Contracts;

import it61.springlabs.data.exceptions.DomainException;

import java.util.UUID;

public interface CrudServiceContract<T, TDTO> {
    T FindById(UUID id) throws DomainException;
    void Delete(UUID id) throws DomainException;
    Iterable<T> Paginated(Integer perPage, Integer page) throws DomainException;
    T Create(TDTO data) throws  DomainException;
    T Update(UUID id, TDTO data) throws DomainException;
}
