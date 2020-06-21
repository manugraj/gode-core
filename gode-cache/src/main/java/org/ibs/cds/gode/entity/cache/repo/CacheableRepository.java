package org.ibs.cds.gode.entity.cache.repo;

import org.apache.commons.collections4.CollectionUtils;
import org.ibs.cds.gode.entity.cache.CacheableEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;
import org.ibs.cds.gode.util.StreamUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
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

    @Override
    public List<Entity> findByIdIn(List<Id> id) {
        return CollectionUtils.isEmpty(id) ? Collections.emptyList()
                : StreamUtils.toList(repo.findAllById(() -> id.iterator()));
    }
}
