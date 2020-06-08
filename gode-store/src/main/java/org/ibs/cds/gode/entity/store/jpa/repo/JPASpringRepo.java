package org.ibs.cds.gode.entity.store.jpa.repo;

import org.ibs.cds.gode.entity.store.StoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.stream.Stream;

public interface JPASpringRepo<Entity extends StoreEntity<Id>,Id extends Serializable> extends JpaRepository<Entity, Id> {

    Stream<Entity> findByActive(boolean active);
    Page<Entity> findByActive(boolean active, Pageable pageable);
    Entity findByAppId(Long appId);
}
