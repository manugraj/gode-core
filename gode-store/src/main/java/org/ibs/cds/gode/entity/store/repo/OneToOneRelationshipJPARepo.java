package org.ibs.cds.gode.entity.store.repo;

import org.ibs.cds.gode.entity.type.OnetoOneRJPAEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface OneToOneRelationshipJPARepo
        <Entity extends OnetoOneRJPAEntity<?,?,aid,bid>,
        aid extends Serializable,
        bid extends Serializable>
        extends JPAEntityRepo<Entity,Long>{

    Optional<Entity> findByAidAndActiveTrue(aid aid);

    Optional<Entity> findByBidAndActiveTrue(bid bid);

    Optional<Entity> findByAidAndBidAndActiveTrue(aid aid, bid bid);

    Optional<Entity> findByAidOrBidAndActiveTrue(aid aid, bid bid);
}
