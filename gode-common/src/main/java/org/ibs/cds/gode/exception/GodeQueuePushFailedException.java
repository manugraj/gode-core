package org.ibs.cds.gode.exception;

public class GodeQueuePushFailedException extends GodeException {
    public GodeQueuePushFailedException(Error error) {
        super(error);
    }

    public GodeQueuePushFailedException(Error error, String message) {
        super(error, message);
    }

    public GodeQueuePushFailedException(Error error, String message, Throwable cause) {
        super(error, message, cause);
    }

    public GodeQueuePushFailedException(Error error, Throwable cause) {
        super(error, cause);
    }

    public GodeQueuePushFailedException(Error error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(error, message, cause, enableSuppression, writableStackTrace);
    }
}
