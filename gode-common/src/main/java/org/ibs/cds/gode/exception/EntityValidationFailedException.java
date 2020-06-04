package org.ibs.cds.gode.exception;

public class EntityValidationFailedException extends EntityOperationFailedException {

    public EntityValidationFailedException(Error error) {
        super(error);
    }

    public EntityValidationFailedException(Error error, String message) {
        super(error, message);
    }

    public EntityValidationFailedException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public EntityValidationFailedException(Error error, Throwable cause) {
        super(error, cause);
    }
}
