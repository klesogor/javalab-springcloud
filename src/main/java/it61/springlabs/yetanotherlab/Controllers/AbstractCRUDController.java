package it61.springlabs.yetanotherlab.Controllers;

import it61.springlabs.yetanotherlab.Exceptions.DomainException;

import java.util.UUID;

public abstract class AbstractCRUDController<T,TDTO> {
    public abstract Iterable<T> getAll(Integer page,Integer perPage) throws DomainException;
    public abstract T findById(UUID id) throws DomainException;
    public abstract T create(TDTO dto) throws DomainException;
    public abstract T update( UUID id ,TDTO dto) throws DomainException;
    public abstract void delete(UUID id) throws DomainException;
}
