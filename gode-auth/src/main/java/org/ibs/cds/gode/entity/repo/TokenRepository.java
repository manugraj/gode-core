package org.ibs.cds.gode.entity.repo;

import org.ibs.cds.gode.entity.cache.repo.CacheRepo;
import org.ibs.cds.gode.entity.cache.repo.CacheRepository;
import org.ibs.cds.gode.entity.type.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenRepository extends CacheRepository<Token,String>{
    @Autowired
    public TokenRepository(CacheRepo<Token, String> cacheRepo) {
        super(cacheRepo);
    }
}
