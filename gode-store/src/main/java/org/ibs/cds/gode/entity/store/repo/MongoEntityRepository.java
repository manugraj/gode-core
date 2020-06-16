package org.ibs.cds.gode.entity.store.repo;

import com.querydsl.core.types.Predicate;
import org.ibs.cds.gode.entity.type.MongoEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class MongoEntityRepository<Entity extends MongoEntity<Id>, Id extends Serializable,Repo extends MongoEntityRepo<Entity,Id>> implements StoreEntityRepo<Entity,Id> {

    private Repo repo;

    public MongoEntityRepository(Repo repo) {
        this.repo = repo;
    }

    @Override
    public Entity findByAppId(Long appId) {
        return repo.findByAppId(appId);
    }

    @Override
    public Optional<Entity> findById(Id id) {
        return repo.findById(id);
    }

    @Override
    public Stream<Entity> findByActive(boolean enabled) {
        return repo.findByActive(enabled);
    }

    @Override
    public PagedData<Entity> findByActive(boolean enabled, PageContext pageable) {
        return null;
    }

    @Override
    public Entity save(Entity entity) {
        return repo.save(entity);
    }

    @Override
    public PagedData<Entity> findAll(PageContext context) {
        return PageUtils.getData(pc-> repo.findAll(pc), context);
    }

    @Override
    public PagedData<Entity> findAll(Predicate predicate, PageContext context) {
        return PageUtils.getData( pc-> repo.findAll(predicate, pc), context, predicate);
    }
}
