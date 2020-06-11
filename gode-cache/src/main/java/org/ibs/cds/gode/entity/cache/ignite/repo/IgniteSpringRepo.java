package org.ibs.cds.gode.entity.cache.ignite.repo;

import org.apache.ignite.springdata20.repository.IgniteRepository;
import org.ibs.cds.gode.entity.cache.CacheEntity;

import java.io.Serializable;

public interface IgniteSpringRepo<Entity extends CacheEntity<Id>, Id extends Serializable> extends IgniteRepository<Entity,Id> {
}
