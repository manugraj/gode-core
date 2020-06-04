package org.ibs.cds.gode.exception;

public class GodeStartupException extends GodeException {


    public GodeStartupException(Error error) {
        super(error);
    }

    public GodeStartupException(Error error, String message) {
        super(error, message);
    }

    public GodeStartupException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public GodeStartupException(Error error, Throwable cause) {
        super(error, cause);
    }

    public GodeStartupException(Error error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
