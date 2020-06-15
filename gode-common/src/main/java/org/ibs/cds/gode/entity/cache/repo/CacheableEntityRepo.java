package org.ibs.cds.gode.entity.cache.repo;

import org.ibs.cds.gode.entity.repo.Repo;
import org.ibs.cds.gode.entity.repo.RepoType;
import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;

import java.io.Serializable;

public interface CacheableEntityRepo<Entity extends TypicalEntity<Id>,Id extends Serializable> extends Repo<Entity,Id> {

    PagedData<Entity> findAll(PageContext context);

    @Override
    default RepoType type(){
        return RepoType.CACHE;
    };
}
