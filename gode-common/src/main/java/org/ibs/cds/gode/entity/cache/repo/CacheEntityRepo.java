package org.ibs.cds.gode.entity.cache.repo;

import org.ibs.cds.gode.entity.cache.CacheEntity;
import org.ibs.cds.gode.entity.repo.Repo;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public interface CacheEntityRepo<Entity extends CacheEntity<Id>,Id extends Serializable> extends Repo<Entity,Id> {
    Optional<StoreEntity<Id>> findById(Id id);
    Stream<StoreEntity<Id>> findByActive(boolean enabled);
    Page<StoreEntity<Id>> findByActive(boolean enabled, Pageable pageable);
    boolean deleteById(Id id);
    StoreEntity<Id> save(StoreEntity<Id> entity);
    boolean deactivate(Id id);
    Page<StoreEntity<Id>> findAll(Pageable pageable);
}
