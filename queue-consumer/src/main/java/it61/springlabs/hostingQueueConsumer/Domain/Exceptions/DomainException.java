package it61.springlabs.hostingQueueConsumer.Domain.Exceptions;

public abstract class DomainException extends Exception {

    protected Integer code;
    protected String description;

    public DomainException(Integer code, String description) {
        super(description);
        this.code = code;
        this.description = description;
    }

    public Integer getCode(){
        return code;
    }

    public String getDescription(){
        return  description;
    }
}
