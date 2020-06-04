package org.ibs.cds.gode.entity.repo;

import org.apache.ignite.springdata20.repository.config.RepositoryConfig;
import org.ibs.cds.gode.entity.cache.repo.IgniteRepo;
import org.ibs.cds.gode.entity.type.Token;
import org.springframework.context.annotation.Configuration;

@Configuration
@RepositoryConfig(cacheName = "tokencache")
public interface TokenRepo extends IgniteRepo<Token,String> {
}
