package org.ibs.cds.gode.entity.manager;

import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.store.repo.StoreEntityRepo;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;

import java.io.Serializable;

@Slf4j
public abstract class StoredStateEntityManager<View extends EntityView<Id>,Entity extends StoreEntity<Id>,Id extends Serializable,Repo extends StoreEntityRepo<Entity,Id>> extends StateEntityManager<View,Entity,Id,Repo> {
    public StoredStateEntityManager(Repo repo) {
        super(repo);
    }

    public PagedData<View> find(PageContext context){
        log.debug(LOG_TEMPLATE, FIND_ALL, context);
        return transformEntityPage(repo.findAll(context));
    }

    public PagedData<View> find(Predicate predicate, PageContext context){
        log.debug(LOG_TEMPLATE, FIND_BY_PREDICATE, predicate);
        return transformEntityPage(repo.findAll(predicate, context));
    }
}
