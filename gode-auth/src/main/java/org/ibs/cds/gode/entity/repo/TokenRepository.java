package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.cache.repo.CacheRepository;
import org.ibs.cds.gode.entity.type.Token;
import org.springframework.stereotype.Service;

@Service
public class TokenRepository extends CacheRepository<Token,String, TokenRepo>{

    public TokenRepository(TokenRepo repo) {
        super(repo);
    }
}
