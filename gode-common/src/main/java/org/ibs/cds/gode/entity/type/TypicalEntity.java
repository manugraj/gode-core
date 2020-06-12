package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ibs.cds.gode.util.EntityUtil;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Objects;

public abstract class TypicalEntity<Id extends Serializable> implements Serializable{

    public transient boolean validated;

    @JsonIgnore
    public abstract Id getId();

    public abstract void setId(Id id) ;

    @JsonIgnore
    public boolean isValidated() {
        return validated;
    }

    @JsonIgnore
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public abstract Boolean isActive();

    public abstract void setActive(Boolean active);

    public abstract Date getCreatedOn();

    public abstract void setCreatedOn(Date createdOn) ;

    public abstract Date getUpdatedOn();

    public abstract void setUpdatedOn(Date updatedOn);

    public abstract Long getAppId();

    public abstract void setAppId(Long appId);

    @Override
    public int hashCode() {
        return EntityUtil.hashCode(this);
    }

    @Override
    public String toString() {
        return EntityUtil.toString(this);
    }
}
