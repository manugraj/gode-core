package org.ibs.cds.gode.entity.cache;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.ibs.cds.gode.entity.type.StateEntity;
import org.ibs.cds.gode.entity.view.EntityView;

import java.io.Serializable;

public interface CacheableEntity<Id extends Serializable> extends StateEntity<Id> {
    @JsonIgnore
    Id getId();

    void setId(Id id) ;
}
