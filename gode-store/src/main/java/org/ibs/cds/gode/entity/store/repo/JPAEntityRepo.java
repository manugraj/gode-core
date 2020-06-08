package org.ibs.cds.gode.entity.store.repo;

import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.store.jpa.repo.JPASpringRepo;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.io.Serializable;

public interface JPAEntityRepo<Entity extends StoreEntity<Id>, Id extends Serializable> extends JPASpringRepo<Entity, Id>, QuerydslPredicateExecutor<Entity>{


}
