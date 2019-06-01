package it61.springlabs.vpsservice.Domain.Exceptions;

import java.util.UUID;

public class NotFoundException extends DomainException {
    public NotFoundException(String description) {
        super(404,description);
    }

    public static DomainException of(UUID id, String entity){
        return new NotFoundException(String.format("Not found entity %s with id %s",entity, id));
    }
}
