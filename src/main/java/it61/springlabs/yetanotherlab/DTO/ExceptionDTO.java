package it61.springlabs.yetanotherlab.DTO;

public class ExceptionDTO {
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
