package it61.springlabs.eurekaclient.Controllers;

import it61.springlabs.data.generic.Response;
import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface CRUDControllerInterface<T,INDTO> {
    Response<Iterable<T>> getAll(Integer page, Integer perPage);
    Response<T> findById(UUID id);
    Response<T> create(INDTO dto);
    Response<T> update(UUID id, INDTO dto);
    void delete(UUID id);
}
