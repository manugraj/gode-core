package org.ibs.cds.gode.entity.cache.repo;

import org.ibs.cds.gode.entity.cache.CacheEntity;
import org.ibs.cds.gode.entity.repo.Repo;

import java.io.Serializable;

public interface CacheEntityRepo<Entity extends CacheEntity<Id>,Id extends Serializable> extends Repo<Entity,Id> {
}
