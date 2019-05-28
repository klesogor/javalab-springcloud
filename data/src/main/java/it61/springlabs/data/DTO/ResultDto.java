package it61.springlabs.data.DTO;

public class ResultDto<T> {
    private int code;
    private String error;
    private T data;

    public ResultDto(int code, String error, T data) {
        this.code = code;
        this.error = error;
        this.data = data;
    }

    public ResultDto() {
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

    public void setCode(int code) {
        this.code = code;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setData(T data) {
        this.data = data;
    }
}