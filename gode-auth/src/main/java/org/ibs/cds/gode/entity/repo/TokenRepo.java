package org.ibs.cds.gode.entity.repo;

import org.apache.ignite.springdata20.repository.config.RepositoryConfig;
import org.ibs.cds.gode.entity.cache.MarkCacheRepo;
import org.ibs.cds.gode.entity.cache.repo.CacheRepo;
import org.ibs.cds.gode.entity.type.Token;
import org.springframework.context.annotation.Configuration;

@Configuration
@RepositoryConfig(cacheName = "tokencache")
@MarkCacheRepo
public interface TokenRepo extends CacheRepo<Token,String> {
}
