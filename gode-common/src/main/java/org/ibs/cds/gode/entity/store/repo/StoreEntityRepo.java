package org.ibs.cds.gode.entity.store.repo;

import com.querydsl.core.types.Predicate;
import org.ibs.cds.gode.entity.repo.Repo;
import org.ibs.cds.gode.entity.repo.RepoType;
import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface StoreEntityRepo<Entity extends TypicalEntity<Id>, Id extends Serializable> extends Repo<Entity, Id> {
    Optional<Entity> findById(Id id);

    Stream<Entity> findByActive(boolean enabled);

    PagedData<Entity> findByActive(boolean enabled, PageContext pageable);

    Entity save(Entity entity);

    PagedData<Entity> findAll(PageContext pageable);

    PagedData<Entity> findAll(Predicate predicate, PageContext context);

    List<Entity> findByIdIn(List<Id> ids);

    @Override
    default RepoType type() {
        return RepoType.STORE;
    }
}
