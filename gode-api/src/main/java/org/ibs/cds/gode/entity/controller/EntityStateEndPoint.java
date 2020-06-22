package org.ibs.cds.gode.entity.controller;

import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public class EntityStateEndPoint<View extends EntityView<Id>, Entity extends TypicalEntity<Id>, Manager extends EntityManager<View, Entity, Id>, Id extends Serializable>
        extends EntityProcessEndpoint<View, Manager, Id> {

    protected Manager manager;
    public EntityStateEndPoint(Manager manager) {
        super(manager);
        this.manager = manager;
    }

    @PostMapping(path="/save")
    @ApiOperation("Create/Update entity")
    public Response<View> save(@RequestBody Request<View> request, String url) {
        return Executor.run(Logic.save(), request, manager, KnownException.SAVE_FAILED, url);
    }

    @PostMapping(path="/deactivate")
    @ApiOperation("Deactivate entity")
    public Response<Boolean> deactivate(@RequestBody Request<Id> request, String url) {
        return Executor.run(Logic.deactivate(), request, manager, KnownException.SAVE_FAILED, url);
    }

    @GetMapping(path="/find")
    @ApiOperation("Find entity by id")
    public Response<View> find(Id request, String url) {
        return Executor.run(Logic.findById(), request, manager, KnownException.QUERY_FAILED, url);
    }

    @GetMapping(path="/findAll")
    @ApiOperation("Find all entity instances")
    public Response<PagedData<View>> findAll(@ModelAttribute APIArgument argument, String url) {
        return Executor.run(Logic.findAll0(), PageContext.fromAPI(argument), manager, KnownException.QUERY_FAILED, url);
    }

    @GetMapping(path="/findAll/where")
    @ApiOperation("Find all entity instances dynamically")
    public Response<PagedData<View>> findAllByPredicate(@QuerydslPredicate Predicate predicate, @ModelAttribute APIArgument argument, String url) {
        return Executor.run(Logic.findAllByPredicate0(), PageContext.fromAPI(argument), predicate, manager, KnownException.QUERY_FAILED, url);
    }

}
