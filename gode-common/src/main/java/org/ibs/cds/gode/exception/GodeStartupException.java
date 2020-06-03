package org.ibs.cds.gode.exception;

public class GodeStartupException extends GodeException {

    public GodeStartupException(Error error) {
        super(error);
    }

    public GodeStartupException(String message, Error error) {
        super(message, error);
    }

    public GodeStartupException(String message, Throwable cause, Error error) {
        super(message, cause, error);
    }

    public GodeStartupException(Throwable cause, Error error) {
        super(cause, error);
    }
}
