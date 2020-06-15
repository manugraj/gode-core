package org.ibs.cds.gode.entity.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ibs.cds.gode.entity.type.StateEntity;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.util.EntityUtil;

import java.io.Serializable;

public abstract class StoreEntity<Id extends Serializable> implements StateEntity<Id> {
    public transient boolean validated;

    @JsonIgnore
    public abstract Id getId();

    public abstract void setId(Id id);
    @JsonIgnore
    public abstract IStoreType getStoreType();

    @JsonIgnore
    public boolean isValidated() {
        return validated;
    }

    @JsonIgnore
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    @Override
    public int hashCode() {
        return EntityUtil.hashCode(this);
    }

    @Override
    public String toString() {
        return EntityUtil.toString(this);
    }
}
