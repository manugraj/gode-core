package org.ibs.cds.gode.exception;

public class AuthException extends GodeException {

    public AuthException(Error error) {
        super(error);
    }

    public AuthException(Error error, String message) {
        super(error, message);
    }

    public AuthException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public AuthException(Error error, Throwable cause) {
        super(error, cause);
    }

    public AuthException(Error error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
