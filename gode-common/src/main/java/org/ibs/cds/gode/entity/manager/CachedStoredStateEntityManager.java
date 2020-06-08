package org.ibs.cds.gode.entity.manager;

import com.querydsl.core.types.Predicate;
import org.ibs.cds.gode.entity.cache.CacheEntity;
import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.manager.operation.StateEntityManagerOperation;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class CachedStoredStateEntityManager<View extends EntityView<Id>,Entity extends CacheEntity<Id>,Id extends Serializable,CacheManager extends CachedStateEntityManager<View, Entity,Id,?>,StoreManager extends StoredStateEntityManager<View, Entity,Id,?>>
 implements StateEntityManagerOperation<View, Entity,Id> {

    private final CacheManager cacheManager;
    private  final StoreManager storeManager;

    public CachedStoredStateEntityManager(CacheManager cacheManager, StoreManager storeManager){
        this.storeManager = storeManager;
        this.cacheManager = cacheManager;
    }

    public <T> T getData(Supplier<T> cacheFunction, Supplier<T> storeFuction, Function<T,T> cacheUpdate){
       T cacheValue = cacheFunction.get();
       if(cacheValue == null){
           T storeValue = storeFuction.get();
           return cacheUpdate == null ? storeValue : cacheUpdate.apply(storeValue);
       }
       return cacheValue;
    }

    public <T> T getData(Function<CacheManager, Function<java.util.function.Predicate<T>,Function<StoreManager,T>>> resolver,  Function<T,T> cacheUpdate){
       T data = resolver.apply(cacheManager).apply(Objects::nonNull).apply(storeManager);
       return cacheUpdate == null ? data : cacheUpdate.apply(data);
    }


    @Override
    public View save(View view) {
        return storeManager.save(cacheManager.save(view));
    }

    @Override
    public View findByAppId(Long appId) {
        return getData(()->cacheManager.findByAppId(appId), ()->storeManager.findByAppId(appId), cacheManager::save);
    }

    @Override
    public View find(Id id) {
        return getData(()->cacheManager.find(id), ()->storeManager.find(id), cacheManager::save);
    }

    public PagedData<View> find(PageContext context) {
        return storeManager.find(context);
    }

    public PagedData<View> find(Predicate predicate, PageContext context) {
        return storeManager.find(predicate, context);
    }

    @Override
    public View transformEntity(Entity entity) {
        return cacheManager.transformEntity(entity);
    }

    @Override
    public Entity transformView(View entity) {
        return cacheManager.transformView(entity);
    }

    @Override
    public boolean deactivate(Id id) {
        return cacheManager.deactivate(id) && storeManager.deactivate(id);
    }

    @Override
    public DataMap process(View view) {
        return DataMap.empty();
    }
}
