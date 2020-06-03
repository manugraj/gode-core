package org.ibs.cds.gode.status;

public class Status implements IStatus {

    public Status(int code, String stringRepresentation) {
        this.code = code;
        this.stringRepresentation = stringRepresentation;
    }

    private final int code;
    private final String stringRepresentation;

    @Override
    public int code() {
        return code;
    }

    @Override
    public String toString() {
        return this.stringRepresentation;
    }
}
