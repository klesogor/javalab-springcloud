package it61.springlabs.data.exceptions;

public class AuthException extends DomainException {
    public AuthException(Integer code, String description) {
        super(code, description);
    }

    public static  DomainException of(String description){
        return new AuthException(401,description);
    }
}
