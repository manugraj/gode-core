package org.ibs.cds.gode.exception;

public class GodeRuntimeException extends GodeException{

    public GodeRuntimeException(Error error) {
        super(error);
    }

    public GodeRuntimeException(String message, Error error) {
        super(message, error);
    }

    public GodeRuntimeException(String message, Throwable cause, Error error) {
        super(message, cause, error);
    }

    public GodeRuntimeException(Throwable cause, Error error) {
        super(cause, error);
    }

    public GodeRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Error error) {
        super(message, cause, enableSuppression, writableStackTrace, error);
    }
}
