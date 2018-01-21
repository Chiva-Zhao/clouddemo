package com.zzh.boot.exception;

public class BusinessSubExtendsException extends BusinessExtendsException {

    public BusinessSubExtendsException() {
    }

    public BusinessSubExtendsException(String message) {
        super(message);
    }

    public BusinessSubExtendsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessSubExtendsException(Throwable cause) {
        super(cause);
    }

    public BusinessSubExtendsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}