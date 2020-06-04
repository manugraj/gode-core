package org.ibs.cds.gode.exception;

public class GodeException extends RuntimeException {

    private final Error error;

    public GodeException(Error error) {
        this.error = error;
    }

    public GodeException(Error error, String message) {
        super(message);
        this.error = error;
    }

    public GodeException(Error error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public GodeException(Error error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    public GodeException(Error error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }
}
