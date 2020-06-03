package org.ibs.cds.gode.exception;

import lombok.Data;

public @Data class Error implements IError {
    private int code;
    private String message;
    private String details;

    public Error(int code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public Error(int code, String message) {
        this(code, message, null);
    }


}
