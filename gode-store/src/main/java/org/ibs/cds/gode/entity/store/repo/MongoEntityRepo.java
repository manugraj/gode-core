package org.ibs.cds.gode.entity.store.repo;

import org.ibs.cds.gode.entity.store.mongo.repo.MongoDBSpringRepo;
import org.ibs.cds.gode.entity.type.MongoEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface MongoEntityRepo<Entity extends MongoEntity<Id>, Id extends Serializable> extends MongoDBSpringRepo<Entity,Id>, QuerydslPredicateExecutor<Entity> {
}
