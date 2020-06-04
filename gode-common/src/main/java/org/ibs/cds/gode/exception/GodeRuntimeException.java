package org.ibs.cds.gode.exception;

public class GodeRuntimeException extends GodeException{


    public GodeRuntimeException(Error error) {
        super(error);
    }

    public GodeRuntimeException(Error error, String message) {
        super(error, message);
    }

    public GodeRuntimeException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public GodeRuntimeException(Error error, Throwable cause) {
        super(error, cause);
    }

    public GodeRuntimeException(Error error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
