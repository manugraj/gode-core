package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.store.repo.StoreEntityRepo;
import org.ibs.cds.gode.entity.type.Relationship;

import java.io.Serializable;
import java.util.Optional;

public interface RelationshipRepo <Entity extends Relationship<aid, bid>,
        aid extends Serializable,
        bid extends Serializable> extends StoreEntityRepo<Entity, Long> {

    Optional<Entity> findByAidAndBid(aid aid, bid bid);
}
