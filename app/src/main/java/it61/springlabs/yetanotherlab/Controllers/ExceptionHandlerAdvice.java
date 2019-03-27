package it61.springlabs.yetanotherlab.Controllers;

import it61.springlabs.yetanotherlab.DTO.ExceptionDTO;
import it61.springlabs.yetanotherlab.Exceptions.DomainException;
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
        ExceptionDTO dto = new ExceptionDTO(ex.getDescription(),ex.getCode());
        return handleExceptionInternal(ex, dto, new HttpHeaders(), HttpStatus.resolve(ex.getCode()),req);
    }
}
