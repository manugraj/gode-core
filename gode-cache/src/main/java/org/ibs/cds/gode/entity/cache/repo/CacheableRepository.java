package org.ibs.cds.gode.entity.cache.repo;

import org.ibs.cds.gode.entity.cache.CacheableEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;

import java.io.Serializable;
import java.util.Optional;

public abstract class CacheableRepository<Entity extends CacheableEntity<Id>, Id extends Serializable, Repo extends CacheRepo<Entity, Id>>  implements CacheableEntityRepo<Entity,Id> {

    private final Repo repo;

    public CacheableRepository(Repo repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Entity> findById(Id id) {
        return this.repo.findById(id);
    }

    @Override
    public Entity save(Entity entity) {
        return this.repo.save(entity.getId(), entity);
    }

    @Override
    public PagedData<Entity> findAll(PageContext context){
        return PageUtils.getData(this.repo.findAll(), this.repo.count(), context);
    }


}
