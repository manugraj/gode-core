package org.ibs.cds.gode.entity.type;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
@Table(uniqueConstraints = {@UniqueConstraint(name= "OneToOneRelationshipCheck", columnNames = {"aid", "bid","active"})})
public abstract class OnetoOneRJPAEntity<A extends TypicalEntity<aid>,
        B extends TypicalEntity<bid>,
        aid extends Serializable,
        bid extends Serializable> extends JPAEntity<Long> implements Relationship<aid,bid>{

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
