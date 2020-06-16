package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public interface TypicalEntity<Id extends Serializable> extends Serializable{

    @JsonIgnore
    public abstract Id getId();

    public abstract void setId(Id id) ;

    public abstract Boolean isActive();

    public abstract void setActive(Boolean active);

    public abstract Date getCreatedOn();

    public abstract void setCreatedOn(Date createdOn) ;

    public abstract Date getUpdatedOn();

    public abstract void setUpdatedOn(Date updatedOn);

    public abstract Long getAppId();

    public abstract void setAppId(Long appId);

    void setValidated(boolean validated);

    boolean isValidated();
}
