package org.ibs.cds.gode.exception;

import lombok.Data;

@Data
public class GodeException extends RuntimeException {

    private final Error error;
    private final static String ERROR="%s | %s";
    public GodeException(Error error) {
        this.error = error;
    }

    public GodeException(Error error, String message) {
        super(message(error,message));
        this.error = error;
    }

    public GodeException(Error error, String message, Throwable cause) {
        super(message(error, message), cause);
        this.error = error;
    }

    public GodeException(Error error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    public GodeException(Error error, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message(error,message), cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

    private static String message(Error error, String message){
        if(error == null || error.getMessage() == null) return message;
        if(message == null) return error.getMessage();
        return String.format(ERROR, error.getMessage(), message);
    }
}
