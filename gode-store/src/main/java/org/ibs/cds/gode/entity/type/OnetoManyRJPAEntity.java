package org.ibs.cds.gode.entity.type;

import lombok.Data;


import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.io.Serializable;

@MappedSuperclass
@Data
@Table(indexes = {@Index(unique = true,name = "OneToManyRelationshipCheck", columnList = "bid,active")})
public class OnetoManyRJPAEntity<A extends TypicalEntity<aid>,B extends TypicalEntity<bid>,aid extends Serializable, bid extends Serializable> extends JPAEntity<Long> implements Relationship<aid, bid>{

    private @Id Long relationshipId;
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
