package org.ibs.cds.gode.entity.controller;

import com.querydsl.core.types.Predicate;
import org.ibs.cds.gode.entity.manager.EntityManager;
import org.ibs.cds.gode.entity.operation.Executor;
import org.ibs.cds.gode.entity.operation.Logic;
import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.pagination.PagedData;
import org.ibs.cds.gode.util.APIArgument;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.Serializable;

public class EntityStateEndPoint<View extends EntityView<Id>, Entity extends TypicalEntity<Id>, Manager extends EntityManager<View, Entity, Id>, Id extends Serializable>
        extends EntityProcessEndpoint<View, Manager, Id> {

    protected Manager manager;
    public EntityStateEndPoint(Manager manager) {
        super(manager);
        this.manager = manager;
    }

    public Response<View> save(Request<View> request, String url) {
        return Executor.run(Logic.save(), request, manager, KnownException.SAVE_FAILED, url);
    }

    public Response<Boolean> deactivate(Request<Id> request, String url) {
        return Executor.run(Logic.deactivate(), request, manager, KnownException.SAVE_FAILED, url);
    }

    public Response<View> find(Id request, String url) {
        return Executor.run(Logic.findById(), request, manager, KnownException.QUERY_FAILED, url);
    }

    public Response<PagedData<View>> findAll(@ModelAttribute APIArgument argument, String url) {
        return Executor.run(Logic.findAll0(), PageContext.fromAPI(argument), manager, KnownException.QUERY_FAILED, url);
    }

    public Response<PagedData<View>> findAllByPredicate(@QuerydslPredicate Predicate predicate, @ModelAttribute APIArgument argument, String url) {
        return Executor.run(Logic.findAllByPredicate0(), PageContext.fromAPI(argument), predicate, manager, KnownException.QUERY_FAILED, url);
    }

}
