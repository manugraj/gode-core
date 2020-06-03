package org.ibs.cds.gode.exception;

public class GodeException extends RuntimeException {

    private final Error error;

    public GodeException(Error error) {
        this.error = error;
    }

    public GodeException(String message, Error error) {
        super(message);
        this.error = error;
    }

    public GodeException(String message, Throwable cause, Error error) {
        super(message, cause);
        this.error = error;
    }

    public GodeException(Throwable cause, Error error) {
        super(cause);
        this.error = error;
    }

    public GodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Error error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
