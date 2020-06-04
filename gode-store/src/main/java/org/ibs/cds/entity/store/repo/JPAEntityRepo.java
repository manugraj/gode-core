package org.ibs.cds.entity.store.repo;

import com.querydsl.core.types.Predicate;
import org.ibs.cds.entity.store.jpa.repo.JPASpringRepo;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.store.repo.StoreEntityRepo;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.PageUtils;

import java.io.Serializable;

public interface JPAEntityRepo<Entity extends StoreEntity<Id>, Id extends Serializable> extends JPASpringRepo<Entity, Id>, StoreEntityRepo<Entity,Id> {

    @Override
    default PagedData<Entity> findAll(PageContext context){
        return PageUtils.getData(ctx->findAll(ctx), context);
    };

    @Override
    default PagedData<Entity> findAll(Predicate predicate, PageContext context){
        return PageUtils.getData(ctx -> findAll(predicate, ctx), context);
    };

    @Override
    default PagedData<Entity> findByActive(boolean active, PageContext context){
        return PageUtils.getData(ctx -> findByActive(active, ctx), context);
    };


}
