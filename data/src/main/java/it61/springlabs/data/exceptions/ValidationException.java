package it61.springlabs.data.exceptions;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationException extends DomainException {
    public ValidationException(Integer code, String description) {
        super(code, description);
    }

    public static DomainException of(Set<ConstraintViolation> violations){
        StringBuilder res = new StringBuilder();
        for(ConstraintViolation violation : violations){
            res.append(violation.getMessage());
            res.append("\n");
        }
        return new ValidationException(422, res.toString());
    }
}
