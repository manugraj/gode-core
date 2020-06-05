package org.ibs.cds.gode.entity.type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ibs.cds.gode.util.EntityUtil;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class TypicalEntity<Id extends Serializable> {
    private Id id;
    public transient boolean validated;
    private OffsetDateTime createdOn;
    private OffsetDateTime updatedOn;
    private Long appId;
    private Boolean active;

    public Id getId() {
        return this.id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isValidated() {
        return validated;
    }

    @JsonIgnore
    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

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

    @Override
    public String toString() {
        return EntityUtil.toString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypicalEntity<?> that = (TypicalEntity<?>) o;
        return validated == that.validated &&
                active == that.active &&
                Objects.equals(id, that.id) &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(updatedOn, that.updatedOn) &&
                Objects.equals(appId, that.appId);
    }
}
