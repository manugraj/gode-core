package org.ibs.cds.gode.entity.controller;

import io.swagger.annotations.ApiOperation;
import org.ibs.cds.gode.entity.generic.DataMap;
import org.ibs.cds.gode.entity.manager.EntityViewManager;
import org.ibs.cds.gode.entity.operation.Executor;
import org.ibs.cds.gode.entity.operation.Logic;
import org.ibs.cds.gode.entity.validation.ValidationStatus;
import org.ibs.cds.gode.entity.view.EntityView;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

@Controller
public abstract class EntityProcessEndpoint<View extends EntityView<Id>, Manager extends EntityViewManager<View, Id>, Id extends Serializable> {

    private static final String VALIDATE = "/validate";
    private static final String PROCESS = "/process";
    private Manager manager;

    public EntityProcessEndpoint(Manager manager) {
        this.manager = manager;
    }

    @PostMapping(path= VALIDATE)
    @ApiOperation("Validate entity")
    public Response<ValidationStatus> validate(@RequestBody Request<View> request) {
        return Executor.run(Logic.validate(), request, manager, KnownException.ENTITY_VALIDATIONS_FAILED, VALIDATE);
    }

    @PostMapping(path= PROCESS)
    @ApiOperation("Process entity")
    public Response<DataMap> process(@RequestBody Request<View> request) {
        return Executor.run(Logic.process(), request, manager, KnownException.ENTITY_OPERATION_FAILED, PROCESS);
    }
}
