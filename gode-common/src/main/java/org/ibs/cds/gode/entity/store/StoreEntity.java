package org.ibs.cds.gode.entity.store;

import org.ibs.cds.gode.entity.TypicalEntity;

import java.io.Serializable;

public abstract class StoreEntity<Id extends Serializable> extends TypicalEntity<Id> {

    private Id id;

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }
    public abstract IStoreType getStoreType();
}
