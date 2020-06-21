package org.ibs.cds.gode.entity.type;

import com.querydsl.core.annotations.QuerySupertype;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@QuerySupertype
@Data
public abstract class ManytoAnyRMongoEntity<A extends TypicalEntity<aid>,B extends TypicalEntity<bid>,aid extends Serializable, bid extends Serializable> extends MongoEntity<Long> implements Relationship<aid,bid>{

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
