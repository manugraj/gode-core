package org.ibs.cds.gode.entity.manager.operation;

import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.validation.ValidationStatus;

import java.io.Serializable;

public interface ViewEntityManagerOperation<T extends TypicalEntity<Id>,Id extends Serializable> {

    DataMap process(T t);
    default ValidationStatus validateView(T t){
        return ValidationStatus.ok();
    }

}
