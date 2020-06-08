package org.ibs.cds.gode.entity.cache.repo;

import org.ibs.cds.gode.entity.cache.CacheEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;

import java.io.Serializable;
import java.util.Optional;

public abstract class CacheRepository<Entity extends CacheEntity<Id>, Id extends Serializable, Repo extends CacheRepo<Entity, Id>>  implements CacheEntityRepo<Entity,Id> {

    private final Repo repo;

    public CacheRepository(Repo repo) {
        this.repo = repo;
    }

    @Override
    public Entity findByAppId(Long appId) {
        return repo.findByAppId(appId);
    }

    @Override
    public Optional<Entity> findById(Id id) {
        return this.repo.findById(id);
    }

    @Override
    public Entity save(Entity entity) {
        return this.repo.save(entity);
    }

    public PagedData<Entity> findAll(PageContext context){
        return PageUtils.getData(this.repo.findAll(), this.repo.count(), context);
    }
}
