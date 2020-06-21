package org.ibs.cds.gode.entity.store.repo;

import org.ibs.cds.gode.entity.type.ManytoAnyRMongoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface ManyToAnyRelationshipMongoRepo
        <Entity extends ManytoAnyRMongoEntity<?,?,aid,bid>,
        aid extends Serializable,
        bid extends Serializable>
        extends MongoEntityRepo<Entity,Long>{

    Page<Entity> findByAidAndActiveTrue(aid aid, Pageable context);

    Page<Entity> findByBidAndActiveTrue(bid bid, Pageable context);

    Optional<Entity> findByAidAndBidAndActiveTrue(aid aid, bid bid);

}
