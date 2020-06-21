package org.ibs.cds.gode.entity.type;

import com.querydsl.core.annotations.QuerySupertype;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@QuerySupertype
@Data
@CompoundIndexes({
        @CompoundIndex(unique = true,name = "OneToManyRelationshipCheck", def = "{'bid' : 1, 'active' :1}")
})
public class OnetoManyRMongoEntity<A extends TypicalEntity<aid>,B extends TypicalEntity<bid>,aid extends Serializable, bid extends Serializable> extends MongoEntity<Long> implements Relationship<aid,bid>{

    private @Id Long relationshipId;
    @Indexed(unique = true)
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
