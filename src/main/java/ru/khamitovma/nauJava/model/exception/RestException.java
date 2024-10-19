package ru.khamitovma.nauJava.model.exception;

public class RestException {
    private String message;
    private RestException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public static RestException create(Throwable e) {
        return new RestException(e.getMessage());
    }
    public static RestException create(String message) {
        return new RestException(message);
    }
}
