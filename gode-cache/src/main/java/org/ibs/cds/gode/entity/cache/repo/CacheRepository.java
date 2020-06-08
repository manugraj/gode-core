package org.ibs.cds.gode.entity.cache.repo;

import org.ibs.cds.gode.entity.cache.CacheEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;

import java.io.Serializable;
import java.util.Optional;

public class CacheRepository<Entity extends CacheEntity<Id>, Id extends Serializable>  implements CacheEntityRepo<Entity,Id> {

    private final CacheRepo<Entity,Id> cacheRepo;

    public CacheRepository(CacheRepo<Entity, Id> cacheRepo) {
        this.cacheRepo = cacheRepo;
    }

    @Override
    public Entity findByAppId(Long appId) {
        return cacheRepo.findByAppId(appId);
    }

    @Override
    public Optional<Entity> findById(Id id) {
        return this.cacheRepo.findById(id);
    }

    @Override
    public Entity save(Entity entity) {
        return this.cacheRepo.save(entity);
    }

    public PagedData<Entity> findAll(PageContext context){
        return PageUtils.getData(this.cacheRepo.findAll(), this.cacheRepo.count(), context);
    }
}
