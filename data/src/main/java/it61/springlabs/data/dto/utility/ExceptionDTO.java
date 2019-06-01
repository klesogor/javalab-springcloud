package it61.springlabs.data.dto.utility;

public final class ExceptionDTO {
    private String error;
    private Integer code;

    public ExceptionDTO(String error, Integer code) {
        this.error = error;
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public Integer getCode() {
        return code;
    }
}
