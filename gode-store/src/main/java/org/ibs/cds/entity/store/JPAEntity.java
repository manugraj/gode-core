package org.ibs.cds.entity.store;

import org.ibs.cds.gode.entity.store.StoreEntity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class JPAEntity<Id extends Serializable> extends StoreEntity<Id> {
    private @javax.persistence.Id Id id;

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }
    @Override
    public StoreType getStoreType() {
        return StoreType.JPA;
    }
}
