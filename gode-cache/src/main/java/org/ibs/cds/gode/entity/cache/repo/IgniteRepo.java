package org.ibs.cds.gode.entity.cache.repo;

import org.ibs.cds.gode.entity.cache.CacheEntity;
import org.ibs.cds.gode.entity.cache.ignite.repo.IgniteSpringRepo;

import java.io.Serializable;

public interface IgniteRepo<Entity extends CacheEntity<Id>, Id extends Serializable> extends IgniteSpringRepo<Entity,Id>,CacheEntityRepo<Entity,Id> {


}
