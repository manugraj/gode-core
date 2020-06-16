package org.ibs.cds.gode.entity.cache.repo;

import org.ibs.cds.gode.entity.cache.ignite.repo.IgniteSpringRepo;
import org.ibs.cds.gode.entity.cache.CacheableEntity;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CacheRepo<Entity extends CacheableEntity<Id>, Id extends Serializable> extends IgniteSpringRepo<Entity,Id> {

}
