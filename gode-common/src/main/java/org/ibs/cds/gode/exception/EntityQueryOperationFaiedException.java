package org.ibs.cds.gode.exception;

public class EntityQueryOperationFaiedException extends EntityOperationFailedException {

    public EntityQueryOperationFaiedException(Error error) {
        super(error);
    }

    public EntityQueryOperationFaiedException(Error error, String message) {
        super(error, message);
    }

    public EntityQueryOperationFaiedException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public EntityQueryOperationFaiedException(Error error, Throwable cause) {
        super(error, cause);
    }
}
