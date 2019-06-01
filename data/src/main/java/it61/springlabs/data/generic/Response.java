package it61.springlabs.data.generic;

public class Response<T> {
    Error error;
    T data;

    public Response(Error error, T data) {
        this.error = error;
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public static <T> Response<T> Of(T data){
        return new Response<>(Error.empty(),data);
    }

    public static <T> Response<T> Error(String error){
        Error err = new Error(true, error);
        return new Response<>(err,null);
    }
}
