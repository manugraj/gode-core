package org.ibs.cds.gode.entity.store.repo;

import com.querydsl.core.types.Predicate;
import org.apache.commons.collections4.CollectionUtils;
import org.ibs.cds.gode.entity.type.JPAEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class JPAEntityRepository<Entity extends JPAEntity<Id>, Id extends Serializable, Repo extends JPAEntityRepo<Entity,Id>> implements StoreEntityRepo<Entity,Id> {

    protected final Repo repo;

    public JPAEntityRepository(Repo repo) {
        this.repo = repo;
    }

    @Override
    public Optional<Entity> findByAppId(Long appId) {
        return repo.findByAppId(appId).filter(JPAEntity::isActive);
    }

    @Override
    public Optional<Entity> findById(Id id) {
        return repo.findById(id).filter(JPAEntity::isActive);
    }

    @Override
    public List<Entity> findByIdIn(List<Id> id) {
        return CollectionUtils.isEmpty(id) ? List.of() :this.repo.findAllById(()->id.iterator());
    }

    @Override
    public Stream<Entity> findByActive(boolean enabled) {
        return repo.findByActive(enabled);
    }

    @Override
    public PagedData<Entity> findByActive(boolean enabled, PageContext pageable) {
        return PageUtils.getData( pc-> repo.findByActive(enabled, pc), pageable);
    }

    @Override
    public Entity save(Entity entity) {
        return repo.save(entity);
    }

    @Override
    public PagedData<Entity> findAll(PageContext context) {
        return PageUtils.getData( pc-> repo.findAll(pc), context);
    }

    @Override
    public PagedData<Entity> findAll(Predicate predicate, PageContext context) {
        return PageUtils.getData( pc-> repo.findAll(predicate, pc), context,predicate);
    }
}
