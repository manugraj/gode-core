package org.ibs.cds.gode.entity.validation;

import java.io.Serializable;

public class ValidationFailed implements Serializable {
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String fieldName;
    private String message;

    public ValidationFailed() {
    }

    public ValidationFailed(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
}
