package org.ibs.cds.gode.entity.manager;

import lombok.extern.slf4j.Slf4j;
import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.manager.operation.ViewEntityManagerOperation;
import org.ibs.cds.gode.entity.validation.ValidationStatus;
import org.ibs.cds.gode.entity.view.EntityView;

import java.io.Serializable;

@Slf4j
public class EntityViewManager<View extends EntityView<Id>,
        Id extends Serializable>
        implements ViewEntityManagerOperation<View, Id> {

    public EntityViewManager(){

    }


    @Override
    public DataMap process(View view) {
        return DataMap.empty();
    }

    @Override
    public ValidationStatus validateView(View view) {
        return ValidationStatus.ok();
    }
}
