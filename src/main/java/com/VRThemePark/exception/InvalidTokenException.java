package com.VRThemePark.exception;

public class InvalidTokenException extends RuntimeException {
    private int status;
    private String message;

    public InvalidTokenException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
