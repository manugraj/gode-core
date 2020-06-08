package org.ibs.cds.gode.entity.store.repo;

import com.querydsl.core.types.Predicate;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class JPAEntityRepository<Entity extends StoreEntity<Id>, Id extends Serializable> implements StoreEntityRepo<Entity,Id> {

    private final JPAEntityRepo<Entity,Id> jpaEntityRepo;

    public JPAEntityRepository(JPAEntityRepo<Entity, Id> jpaEntityRepo) {
        this.jpaEntityRepo = jpaEntityRepo;
    }

    @Override
    public Entity findByAppId(Long appId) {
        return jpaEntityRepo.findByAppId(appId);
    }

    @Override
    public Optional<Entity> findById(Id id) {
        return jpaEntityRepo.findById(id);
    }

    @Override
    public Stream<Entity> findByActive(boolean enabled) {
        return jpaEntityRepo.findByActive(enabled);
    }

    @Override
    public PagedData<Entity> findByActive(boolean enabled, PageContext pageable) {
        return null;
    }

    @Override
    public Entity save(Entity entity) {
        return jpaEntityRepo.save(entity);
    }

    @Override
    public PagedData<Entity> findAll(PageContext context) {
        return PageUtils.getData( pc-> jpaEntityRepo.findAll(pc), context);
    }

    @Override
    public PagedData<Entity> findAll(Predicate predicate, PageContext context) {
        return PageUtils.getData( pc-> jpaEntityRepo.findAll(predicate, pc), context);
    }
}
