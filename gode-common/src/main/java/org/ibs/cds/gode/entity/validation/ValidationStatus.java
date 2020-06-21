package org.ibs.cds.gode.entity.validation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.ibs.cds.gode.status.BinaryStatus;
import org.ibs.cds.gode.status.IStatus;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class ValidationStatus implements IStatus, Serializable {

    public ValidationStatus(BinaryStatus status) {
        this.status = status;
    }

    private BinaryStatus status;
    private ArrayList<ValidationFailed> errors;

    @JsonIgnore
    public void addError(ValidationFailed failed){
        if(errors == null) errors = new ArrayList();
        errors.add(failed);
    }

    @Override
    public int code() {
        return status.code();
    }

    public static ValidationStatus ok(){
        return new ValidationStatus(BinaryStatus.SUCCESS);
    }
}
