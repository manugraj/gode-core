package org.ibs.cds.gode.entity.controller;

import org.ibs.cds.gode.entity.manager.operation.StateEntityManagerOperation;
import org.ibs.cds.gode.entity.operation.Executor;
import org.ibs.cds.gode.entity.operation.Logic;
import org.ibs.cds.gode.entity.type.TypicalEntity;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

@Controller
public abstract class EntityStateChangeEndpoint<View extends EntityView<Id>, Entity extends TypicalEntity<Id>, Manager extends StateEntityManagerOperation<View, Entity, Id>, Id extends Serializable>
        extends EntityProcessEndpoint<View, Manager, Id>{

    private Manager manager;

    public EntityStateChangeEndpoint(Manager manager) {
        super(manager);
        this.manager = manager;
    }

    public Response<View> save(Request<View> request, String url) {
        return Executor.run(Logic.save(), request, manager, KnownException.SAVE_FAILED, url);
    }

    public Response<Boolean> deactivate(Request<Id> request, String url) {
        return Executor.run(Logic.deactivate(), request, manager, KnownException.SAVE_FAILED, url);
    }

    public Response<View> find(Request<Id> request, String url) {
        return Executor.run(Logic.findById(), request, manager, KnownException.QUERY_FAILED, url);
    }
}
