package org.ibs.cds.gode.entity.manager;

import org.ibs.cds.gode.entity.cache.CacheEntity;
import org.ibs.cds.gode.entity.cache.repo.CacheEntityRepo;
import org.ibs.cds.gode.entity.view.EntityView;

import java.io.Serializable;

public abstract class CachedStateEntityManager<View extends EntityView<Id>,Entity extends CacheEntity<Id>,Id extends Serializable,Repo extends CacheEntityRepo<Entity,Id>> extends StateEntityManager<View,Entity,Id,Repo> {
    public CachedStateEntityManager(Repo repo) {
        super(repo);
    }
}
