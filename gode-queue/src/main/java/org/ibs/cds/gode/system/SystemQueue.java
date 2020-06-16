package org.ibs.cds.gode.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ibs.cds.gode.entity.manager.GeneralData;
import org.ibs.cds.gode.entity.manager.GeneralQueueManager;
import org.ibs.cds.gode.entity.operation.Processor;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@QueueEndPoint
@Api(tags={"System queue"})
public class SystemQueue{

    @Autowired
    private GeneralQueueManager generalQueueManager;

    @PostMapping(path="/push")
    @ApiOperation(value = "Operation to push data to queue")
    public Response<Boolean> push(@RequestBody Request<List<GeneralData>> generalDataRequest){
        List<GeneralData> data = generalDataRequest.getData();
        return Processor.successResponse(generalQueueManager.push(data.toArray(new GeneralData[0])), generalDataRequest, "/push");
    }
}
