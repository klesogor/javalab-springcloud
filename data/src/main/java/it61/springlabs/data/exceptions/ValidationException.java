package it61.springlabs.data.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationException extends DomainException {
    public ValidationException(Integer code, String description) {
        super(code, description);
    }

    public static DomainException of(BindingResult result){
        StringBuilder res = new StringBuilder();
        for (FieldError field: result.getFieldErrors()) {
            res.append(field.toString());
            res.append("\n");
        }
        return new ValidationException(422, res.toString());
    }
}
