package org.ibs.cds.gode.entity.view;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Objects;

public abstract class RelationshipView<Aside,Bside> extends TypicalEntityView<Long> {

    private Date createdOn;
    private Date updatedOn;
    private Long appId;
    private boolean active;
    public transient boolean validated;
    private Long relationshipId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationshipView that = (RelationshipView) o;
        return validated == that.validated &&
                active == that.active &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(createdOn, that.createdOn) &&
                Objects.equals(updatedOn, that.updatedOn) &&
                Objects.equals(appId, that.appId);
    }

    @Override
    public Long getId() {
        return relationshipId;
    }

    @Override
    public void setId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    @JsonIgnore
    public abstract Aside getAside() ;
    public abstract void setAside(Aside aside);

    @JsonIgnore
    public abstract Bside getBside();

    public abstract void setBside(Bside bside);
}
