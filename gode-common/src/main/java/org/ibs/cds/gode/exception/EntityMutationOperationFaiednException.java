package org.ibs.cds.gode.exception;

public class EntityMutationOperationFaiednException extends EntityOperationFailedException {

    public EntityMutationOperationFaiednException(Error error) {
        super(error);
    }

    public EntityMutationOperationFaiednException(Error error, String message) {
        super(error, message);
    }

    public EntityMutationOperationFaiednException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public EntityMutationOperationFaiednException(Error error, Throwable cause) {
        super(error, cause);
    }
}
