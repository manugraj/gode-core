package org.ibs.cds.gode.entity.store.repo;

import com.querydsl.core.types.Predicate;
import org.ibs.cds.gode.entity.repo.Repo;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public interface StoreEntityRepo<Id extends Serializable, AppId extends Serializable> extends QuerydslPredicateExecutor<StoreEntity<Id>>, Repo<StoreEntity<Id>,Id> {
    Optional<StoreEntity<Id>> findById(Id id);
    Stream<StoreEntity<Id>> findByActive(boolean enabled);
    Page<StoreEntity<Id>> findByActive(boolean enabled, Pageable pageable);
    boolean deleteById(Id id);
    StoreEntity<Id> save(StoreEntity<Id> entity);
    boolean deactivate(Id id);
    Page<StoreEntity<Id>> findAll(Predicate predicate, Pageable pageable);
    Page<StoreEntity<Id>> findAll(Pageable pageable);
}
