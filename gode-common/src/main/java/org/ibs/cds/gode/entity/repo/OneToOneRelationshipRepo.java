package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.type.Relationship;

import java.io.Serializable;
import java.util.Optional;

public interface OneToOneRelationshipRepo
        <Entity extends Relationship<aid, bid>,
                aid extends Serializable,
                bid extends Serializable> extends RelationshipRepo<Entity, aid, bid> {

    Optional<Entity> findByAid(aid aid);

    Optional<Entity> findByBid(bid bid);

    Optional<Entity> findByAidOrBid(aid aid, bid bid);
}
