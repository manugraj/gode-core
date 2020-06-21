package org.ibs.cds.gode.entity.store.repo;

import org.ibs.cds.gode.entity.repo.ManyToAnyRelationshipRepo;
import org.ibs.cds.gode.entity.type.ManytoAnyRMongoEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;

import java.io.Serializable;
import java.util.Optional;

public  class ManyToAnyRelationshipMongoRepository
        <Entity extends ManytoAnyRMongoEntity<?,?,aid,bid>,
                aid extends Serializable,
                bid extends Serializable,
                Repo extends ManyToAnyRelationshipMongoRepo<Entity,aid,bid>>
        extends MongoEntityRepository<Entity, Long, Repo> implements ManyToAnyRelationshipRepo<Entity,aid,bid> {

    public ManyToAnyRelationshipMongoRepository(Repo repo) {
        super(repo);
    }


    @Override
    public PagedData<Entity> findByAid(aid aid, PageContext context) {
        return PageUtils.getData(c->repo.findByAidAndActiveTrue(aid, c), context);
    }

    @Override
    public PagedData<Entity> findByBid(bid bid, PageContext context) {
        return PageUtils.getData(c->repo.findByBidAndActiveTrue(bid, c), context);
    }

    @Override
    public Optional<Entity> findByAidAndBid(aid aid, bid bid) {
        return this.repo.findByAidAndBidAndActiveTrue(aid, bid);
    }
}
