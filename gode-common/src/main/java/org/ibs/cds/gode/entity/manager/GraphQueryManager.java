package org.ibs.cds.gode.entity.manager;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.querydsl.core.types.Predicate;
import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.manager.operation.StateEntityManagerOperation;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;

import java.io.Serializable;

public abstract class GraphQueryManager<Manager extends SStateEntityManager<View,StoredEntity,Id,?>,View extends EntityView<Id>,StoredEntity extends StoreEntity<Id>,Id extends Serializable>
        implements GraphQLQueryResolver, GraphQLMutationResolver, StateEntityManagerOperation<View, StoredEntity,Id> {

    private Manager manager;
    public GraphQueryManager(Manager manager){
        this.manager = manager;
    }

    @Override
    public View save(View view) {
        return manager.save(view);
    }

    @Override
    public View findByAppId(Long appId) {
        return manager.findByAppId(appId);
    }

    @Override
    public View find(Id id) {
        return manager.find(id);
    }

    public PagedData<View> find(PageContext context) {
        return manager.find(context);
    }

    public PagedData<View> find(Predicate predicate, PageContext context) {
        return manager.find(predicate, context);
    }

    @Override
    public View transformEntity(StoredEntity entity) {
        return manager.transformEntity(entity);
    }

    @Override
    public StoredEntity transformView(View entity) {
        return manager.transformView(entity);
    }

    @Override
    public boolean deactivate(Id id) {
        return manager.deactivate(id);
    }

    @Override
    public DataMap process(View view) {
        return DataMap.empty();
    }

}
