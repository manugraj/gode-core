package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.manager.operation.ViewEntityManagerOperation;
import org.ibs.cds.gode.entity.validation.ValidationStatus;
import org.ibs.cds.gode.entity.view.EntityView;

import java.io.Serializable;

public abstract class ViewEntityManager<View extends EntityView<Id>,Id extends Serializable> implements
        ViewEntityManagerOperation<View,Id> {

    @Override
    public DataMap process(View view) {
        return DataMap.empty();
    }

    @Override
    public ValidationStatus validate(View view) {
        return ValidationStatus.ok();
    }
}
