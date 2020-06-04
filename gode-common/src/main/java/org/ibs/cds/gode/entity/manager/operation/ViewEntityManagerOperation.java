package org.ibs.cds.gode.entity.manager.operation;

import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.validation.ValidationStatus;
import org.ibs.cds.gode.entity.view.EntityView;

import java.io.Serializable;

public interface ViewEntityManagerOperation<View extends EntityView<Id>,Id extends Serializable> {

    DataMap process(View view);
    default ValidationStatus validate(View view){
        return ValidationStatus.ok();
    }

}
