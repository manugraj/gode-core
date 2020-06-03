package org.ibs.cds.gode.entity;

import org.ibs.cds.gode.util.EntityUtil;

import java.io.Serializable;
import java.time.OffsetDateTime;

public abstract class TypicalEntity<Id extends Serializable> {
    private Id id;

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }
    private OffsetDateTime createdOn;
    private OffsetDateTime updatedOn;
    private Long appId;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private boolean active;

    public OffsetDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(OffsetDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public OffsetDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(OffsetDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Override
    public int hashCode() {
        return EntityUtil.hashCode(this);
    }
}
