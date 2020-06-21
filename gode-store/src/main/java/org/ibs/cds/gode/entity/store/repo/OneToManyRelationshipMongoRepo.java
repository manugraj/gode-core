package org.ibs.cds.gode.entity.store.repo;

import org.ibs.cds.gode.entity.type.OnetoManyRMongoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface OneToManyRelationshipMongoRepo
        <Entity extends OnetoManyRMongoEntity<?,?,aid,bid>,
        aid extends Serializable,
        bid extends Serializable>
        extends MongoEntityRepo<Entity,Long>{

    Page<Entity> findByAidAndActiveTrue(aid aid, Pageable pageable);

    Optional<Entity> findByBidAndActiveTrue(bid bid);

    Optional<Entity> findByAidAndBidAndActiveTrue(aid aid, bid bid);


}
