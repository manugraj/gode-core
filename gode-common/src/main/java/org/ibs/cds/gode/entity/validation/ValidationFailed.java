package org.ibs.cds.gode.entity.validation;

import lombok.Data;

import java.io.Serializable;

@Data
public class ValidationFailed implements Serializable {
    private String context;
    private String message;

    public ValidationFailed() {
    }

    public ValidationFailed(String context, String message) {
        this.context = context;
        this.message = message;
    }
}
