package org.ibs.cds.gode.exception;

import lombok.Data;

import java.io.Serializable;

public @Data class Error<Details extends Serializable> implements IError {

    private int code;
    private String message;
    private Details details;

    public Error(int code, String message, Details details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public Error(int code, String message) {
        this(code, message, null);
    }


}
