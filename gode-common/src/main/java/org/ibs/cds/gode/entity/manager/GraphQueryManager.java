package org.ibs.cds.gode.entity.manager;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.manager.operation.StateEntityManagerOperation;
import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.validation.ValidationStatus;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;

import java.io.Serializable;
import java.util.Optional;

public class GraphQueryManager<Manager extends EntityManager<View,Entity,Id>,View extends EntityView<Id>,Entity extends TypicalEntity<Id>,Id extends Serializable>
        implements GraphQLQueryResolver, GraphQLMutationResolver, StateEntityManagerOperation<View, Entity,Id> {

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

    @Override
    public Optional<View> transformEntity(Optional<Entity> entity) {
        return manager.transformEntity(entity);
    }

    @Override
    public Optional<Entity> transformView(Optional<View> entity) {
        return manager.transformView(entity);
    }

    @Override
    public ValidationStatus validateEntity(Entity entity) {
        return manager.validateEntity(entity);
    }

    public PagedData<View> find(PageContext context) {
        return manager.find(context);
    }

    @Override
    public boolean deactivate(Id id) {
        return manager.deactivate(id);
    }

    @Override
    public DataMap process(View view) {
        return DataMap.empty();
    }

    @Override
    public ValidationStatus validateView(View view) {
        return manager.validateView(view);
    }

}
