package com.revature.P1.utils.database.custom_exceptions;

public class InvalidSQLException extends RuntimeException{

    public InvalidSQLException() {
        super();
    }

    public InvalidSQLException(String s) {
        super(s);
    }

    public InvalidSQLException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidSQLException(Throwable throwable) {
        super(throwable);
    }

    protected InvalidSQLException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
