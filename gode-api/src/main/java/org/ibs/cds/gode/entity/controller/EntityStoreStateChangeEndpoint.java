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
import org.ibs.cds.gode.web.Request;
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

    public Response<PagedData<View>> findAll(Request<PageContext> request, String url) {
        return Executor.run(Logic.findAll(), request, manager, KnownException.QUERY_FAILED, url);
    }

    public Response<PagedData<View>> findAllByPredicate(@QuerydslPredicate Predicate predicate, @ModelAttribute PageContext pageContext, String url) {
        return Executor.run(Logic.findAllByPredicate(), pageContext, predicate, manager, KnownException.QUERY_FAILED, url);
    }
}
