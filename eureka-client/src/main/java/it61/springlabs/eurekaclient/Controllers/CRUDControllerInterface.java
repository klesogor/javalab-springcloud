package it61.springlabs.eurekaclient.Controllers;

import org.springframework.validation.BindingResult;

import java.util.UUID;

public interface CRUDControllerInterface<OUTDTO,INDTO> {
    Iterable<OUTDTO> getAll(Integer page, Integer perPage);
    OUTDTO findById(UUID id);
    OUTDTO create(INDTO dto);
    OUTDTO update(UUID id, INDTO dto);
    void delete(UUID id);
}
