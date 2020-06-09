package org.ibs.cds.gode.entity.controller;

import com.querydsl.core.types.Predicate;
import org.ibs.cds.gode.entity.manager.StoredStateEntityManager;
import org.ibs.cds.gode.entity.operation.Executor;
import org.ibs.cds.gode.entity.operation.Logic;
import org.ibs.cds.gode.entity.store.StoreEntity;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.APIArgument;
import org.ibs.cds.gode.web.Response;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serializable;

@Controller
public abstract class EntityStoreStateChangeEndpoint<View extends EntityView<Id>, Entity extends StoreEntity<Id>, Manager extends StoredStateEntityManager<View, Entity, Id,?>, Id extends Serializable>
        extends EntityStateChangeEndpoint<View, Entity,Manager, Id>{

    private Manager manager;

    public EntityStoreStateChangeEndpoint(Manager manager) {
        super(manager);
        this.manager = manager;
    }

    public Response<PagedData<View>> findAll(@ModelAttribute APIArgument argument, String url) {
        return Executor.run(Logic.findAll(), PageContext.fromAPI(argument), manager, KnownException.QUERY_FAILED, url);
    }

    public Response<PagedData<View>> findAllByPredicate(@QuerydslPredicate Predicate predicate, @ModelAttribute APIArgument argument, String url) {
        return Executor.run(Logic.findAllByPredicate(), PageContext.fromAPI(argument), predicate, manager, KnownException.QUERY_FAILED, url);
    }
}
