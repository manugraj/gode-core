package org.ibs.cds.gode.entity.cache.repo;

import org.ibs.cds.gode.entity.cache.CacheEntity;
import org.ibs.cds.gode.entity.cache.ignite.repo.IgniteSpringRepo;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface CacheRepo<Entity extends CacheEntity<Id>, Id extends Serializable> extends IgniteSpringRepo<Entity,Id> {

    Entity findByAppId(Long appId);
}
