package org.ibs.cds.gode.entity.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ibs.cds.gode.entity.view.EntityView;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class StoreEntity<Id extends Serializable> extends EntityView<Id> {

    private Id id;

    @JsonIgnore
    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }
    public abstract IStoreType getStoreType();
}
