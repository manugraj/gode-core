package org.ibs.cds.gode.status;

import com.fasterxml.jackson.annotation.JsonIgnore;

public enum BinaryStatus implements IStatus {
    SUCCESS(0,"Success"),
    FAILURE(1,"Failure");

    BinaryStatus(int code, String stringRepresentation) {
        this.code = code;
        this.stringRepresentation = stringRepresentation;
    }

    @JsonIgnore
    public boolean isSuccess(){
        return SUCCESS == this;
    }

    public static BinaryStatus valueOf(Boolean status){
        return status ? SUCCESS : FAILURE;
    }

    private final int code;
    private final String stringRepresentation;

    @Override @JsonIgnore
    public int code() {
        return code;
    }

    @Override
    public String toString() {
        return this.stringRepresentation;
    }
}
