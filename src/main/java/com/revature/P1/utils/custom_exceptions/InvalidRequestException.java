package com.revature.P1.utils.custom_exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
    }

    public InvalidRequestException(String s) {
        super(s);
    }

    public InvalidRequestException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidRequestException(Throwable throwable) {
        super(throwable);
    }

    protected InvalidRequestException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
