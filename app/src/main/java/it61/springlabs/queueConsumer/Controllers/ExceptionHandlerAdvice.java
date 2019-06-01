package it61.springlabs.queueConsumer.Controllers;

import it61.springlabs.data.exceptions.DomainException;
import it61.springlabs.data.generic.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = DomainException.class)
    protected ResponseEntity<Object> handle(DomainException ex, WebRequest req)
    {
        Response resp = Response.Error(ex.getDescription());
        return handleExceptionInternal(ex, resp, new HttpHeaders(), HttpStatus.resolve(ex.getCode()),req);
    }
}
