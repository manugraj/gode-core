package org.ibs.cds.gode.entity.cache.ignite.repo;

import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.ibs.cds.gode.entity.cache.CacheableEntity;

import java.io.Serializable;

public interface IgniteSpringRepo<Entity extends CacheableEntity<Id>, Id extends Serializable> extends IgniteRepository<Entity,Id> {
}
