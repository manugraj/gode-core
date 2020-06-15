package org.ibs.cds.gode.entity.store.mongo.repo;

import org.ibs.cds.gode.entity.type.MongoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.stream.Stream;
@NoRepositoryBean
public interface MongoDBSpringRepo<Entity extends MongoEntity<Id>,Id extends Serializable> extends MongoRepository<Entity, Id> {
    Stream<Entity> findByActive(boolean active);
    Page<Entity> findByActive(boolean active, Pageable pageable);
    Entity findByAppId(Long appId);
}
