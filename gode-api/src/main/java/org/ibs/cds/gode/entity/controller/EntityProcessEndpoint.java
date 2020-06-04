package org.ibs.cds.gode.entity.controller;

import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.manager.operation.ViewEntityManagerOperation;
import org.ibs.cds.gode.entity.operation.Executor;
import org.ibs.cds.gode.entity.operation.Logic;
import org.ibs.cds.gode.entity.validation.ValidationStatus;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

@Controller
public abstract class EntityProcessEndpoint<View extends EntityView<Id>, Manager extends ViewEntityManagerOperation<View, Id>, Id extends Serializable> {

    private Manager manager;

    public EntityProcessEndpoint(Manager manager) {
        this.manager = manager;
    }

    public Response<ValidationStatus> validate(Request<View> request, String url) {
        return Executor.run(Logic.validate(), request, manager, KnownException.ENTITY_VALIDATIONS_FAILED, url);
    }

    public Response<DataMap> process(Request<View> request, String url) {
        return Executor.run(Logic.process(), request, manager, KnownException.ENTITY_OPERATION_FAILED, url);
    }
}
