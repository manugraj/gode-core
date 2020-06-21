package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@Data
public class ManytoAnyRJPAEntity<A extends TypicalEntity<aid>,B extends TypicalEntity<bid>,aid extends Serializable, bid extends Serializable> extends JPAEntity<Long> implements Relationship<aid,bid>{

    private @Id
    Long relationshipId;
    private aid aid;
    private bid bid;


    @Override
    public Long getId() {
        return relationshipId;
    }

    @Override
    public void setId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

}
