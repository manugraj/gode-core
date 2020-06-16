package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.cache.repo.CacheableEntityRepo;
import org.ibs.cds.gode.entity.manager.operation.StateEntityManagerOperation;
import org.ibs.cds.gode.entity.store.repo.StoreEntityRepo;
import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.queue.manager.QueueManager;
import org.ibs.cds.gode.queue.manager.QueueRepository;
import org.ibs.cds.gode.queue.manager.QueueRepositoryProperties;
import org.ibs.cds.gode.queue.manager.kafka.serialisation.RationalJsonDeserializer;

import java.io.Serializable;
import java.util.Optional;

public abstract class AsyncEntityManager<View extends EntityView<Id>, Entity extends TypicalEntity<Id>,
        Id extends Serializable> extends EntityManager<View, Entity,Id>
        implements StateEntityManagerOperation<View, Entity, Id> {

    protected static final String SAVE = "save";
    protected static final String BFR_SAVE = "beforeSave";
    protected static final String AFR_SAVE = "afterSave";
    protected static final String DO_SAVE = "doSave";
    protected static final String FIND_BY_ID = "findById";
    protected static final String FIND_BY_APPID = "findByAppId";
    protected static final String VALIDATE = "validate";
    protected static final String FIND_ALL = "findAll";
    protected static final String FIND_BY_PREDICATE = "findByPredicate";
    protected static final String DEACTIVATE = "deactivate";
    protected static final String LOG_TEMPLATE = "Action : {} | Arguments: {}";
    protected static final String LOG_TEMPLATE2 = "Action : {} | Arguments: {},{}";

    private QueueManager<Entity> queueManager;
    public <StoreRepo extends StoreEntityRepo<Entity, Id>, CacheRepo extends CacheableEntityRepo<Entity, Id>>
    AsyncEntityManager(StoreRepo storeEntityRepo, CacheRepo cacheableEntityRepo,
                       QueueRepository queueRepository,
                       QueueRepositoryProperties.PusherProperties pusherProperties,
                       QueueRepositoryProperties.SubscriberProperties subscriberProperties,
                       Class<? extends RationalJsonDeserializer<Entity>> deserializer) {
        super(storeEntityRepo, cacheableEntityRepo);
        queueManager = QueueManager.start("",deserializer, queueRepository,pusherProperties, subscriberProperties);
        queueManager.subscribe(k->super.doSave(Optional.ofNullable(k)));
    }


    @Override
    public Optional<Entity> doSave(Optional<Entity> entity) {
        entity.ifPresent(queueManager::push);
        return entity;
    }
}
