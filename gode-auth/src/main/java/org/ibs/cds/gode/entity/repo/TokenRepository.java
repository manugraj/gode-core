package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.cache.repo.CacheableRepository;
import org.ibs.cds.gode.entity.type.Token;
import org.springframework.stereotype.Service;

@Service
public class TokenRepository extends CacheableRepository<Token,String, TokenRepo> {

    public TokenRepository(TokenRepo repo) {
        super(repo);
    }


    @Override
    public Token findByAppId(Long appId) {
        return null;
    }
}
