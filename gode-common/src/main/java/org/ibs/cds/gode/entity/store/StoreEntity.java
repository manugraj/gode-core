package org.ibs.cds.gode.entity.store;

import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.view.EntityView;

import java.io.Serializable;

public abstract class StoreEntity<Id extends Serializable> extends EntityView<Id> {

    private Id id;

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }
    public abstract IStoreType getStoreType();
}
