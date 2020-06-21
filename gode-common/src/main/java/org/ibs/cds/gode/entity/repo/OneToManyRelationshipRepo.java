package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.type.Relationship;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;

import java.io.Serializable;
import java.util.Optional;

public interface OneToManyRelationshipRepo
        <Entity extends Relationship<aid, bid>,
                aid extends Serializable, bid extends Serializable>
        extends RelationshipRepo<Entity, aid, bid> {

    PagedData<Entity> findByAid(aid aid,PageContext context);

    Optional<Entity> findByBid(bid bid);

}
