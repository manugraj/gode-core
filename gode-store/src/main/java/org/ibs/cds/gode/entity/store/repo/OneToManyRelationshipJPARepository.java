package org.ibs.cds.gode.entity.store.repo;

import org.ibs.cds.gode.entity.repo.OneToManyRelationshipRepo;
import org.ibs.cds.gode.entity.type.OnetoManyRJPAEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;

import java.io.Serializable;
import java.util.Optional;

public abstract class OneToManyRelationshipJPARepository
        <Entity extends OnetoManyRJPAEntity<?,?,aid,bid>,
                aid extends Serializable,
                bid extends Serializable,
                Repo extends OneToManyRelationshipJPARepo<Entity,aid,bid>>
        extends JPAEntityRepository<Entity, Long, Repo> implements OneToManyRelationshipRepo<Entity,aid,bid> {

    public OneToManyRelationshipJPARepository(Repo repo) {
        super(repo);
    }


    @Override
    public PagedData<Entity> findByAid(aid aid, PageContext context) {
        return PageUtils.getData(c->this.repo.findByAidAndActiveTrue(aid, c), context);
    }

    @Override
    public Optional<Entity> findByBid(bid bid) {
        return this.repo.findByBidAndActiveTrue(bid);
    }

    @Override
    public Optional<Entity> findByAidAndBid(aid aid, bid bid) {
        return this.repo.findByAidAndBidAndActiveTrue(aid, bid);
    }

}
