package it61.springlabs.data.generic;

public class Error {
    boolean error;
    String message;

    public Error(boolean is_error, String message) {
        this.error = is_error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public static Error empty(){
        return new Error(false, "");
    }
}
