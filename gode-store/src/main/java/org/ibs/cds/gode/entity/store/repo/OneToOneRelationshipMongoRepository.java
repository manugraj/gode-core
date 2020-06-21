package org.ibs.cds.gode.entity.store.repo;

import org.ibs.cds.gode.entity.repo.OneToOneRelationshipRepo;
import org.ibs.cds.gode.entity.type.OnetoOneRJPAEntity;
import org.ibs.cds.gode.entity.type.OnetoOneRMongoEntity;

import java.io.Serializable;
import java.util.Optional;

public abstract class OneToOneRelationshipMongoRepository
        <Entity extends OnetoOneRMongoEntity<?,?,aid,bid>,
                aid extends Serializable,
                bid extends Serializable,
                Repo extends OneToOneRelationshipMongoRepo<Entity,aid,bid>>
        extends MongoEntityRepository<Entity, Long, Repo> implements OneToOneRelationshipRepo<Entity,aid,bid> {

    public OneToOneRelationshipMongoRepository(Repo repo) {
        super(repo);
    }

    @Override
    public Optional<Entity> findByAid(aid aid) {
        return repo.findByAidAndActiveTrue(aid);
    }

    @Override
    public Optional<Entity> findByBid(bid bid) {
        return repo.findByBidAndActiveTrue(bid);
    }

    @Override
    public Optional<Entity> findByAidAndBid(aid aid, bid bid) {
        return repo.findByAidAndBidAndActiveTrue(aid, bid);
    }

    @Override
    public Optional<Entity> findByAidOrBid(aid aid, bid bid) {
        return repo.findByAidOrBidAndActiveTrue(aid, bid);
    }
}
