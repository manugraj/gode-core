package org.ibs.cds.gode.entity.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ibs.cds.gode.entity.view.EntityView;

import java.io.Serializable;

public abstract class StoreEntity<Id extends Serializable> extends EntityView<Id> {

    @JsonIgnore
    public abstract Id getId();

    public abstract void setId(Id id);
    public abstract IStoreType getStoreType();
}
