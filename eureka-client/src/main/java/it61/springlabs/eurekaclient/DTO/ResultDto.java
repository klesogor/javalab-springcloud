package it61.springlabs.eurekaclient.DTO;

public class ResultDto<T> {
    private int code;
    private String error;
    private T data;

    public ResultDto(int code, String error, T data) {
        this.code = code;
        this.error = error;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getError() {
        return error;
    }

    public T getData() {
        return data;
    }
}