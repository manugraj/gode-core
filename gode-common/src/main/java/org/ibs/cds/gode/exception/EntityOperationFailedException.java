package org.ibs.cds.gode.exception;

public class EntityOperationFailedException extends GodeException {

    public EntityOperationFailedException(Error error) {
        super(error);
    }

    public EntityOperationFailedException(Error error, String message) {
        super(error, message);
    }

    public EntityOperationFailedException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public EntityOperationFailedException(Error error, Throwable cause) {
        super(error, cause);
    }
}
