package org.ibs.cds.gode.entity.operation;

import org.ibs.cds.gode.status.BinaryStatus;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.ibs.cds.gode.web.context.RequestContext;
import org.ibs.cds.gode.web.context.ResponseContext;

public class Processor {

    public static <T> Response<T> successResponse(T data, Request<?> request, String handle) {
        ResponseContext respContext=new ResponseContext();
        respContext.setStatus(BinaryStatus.SUCCESS);
        respContext.setRequestContext(request.getContext());
        respContext.setHandle(handle);
        Response<T> response = new Response(data, respContext);
        return response;
    }

    public static <T> Response<T> successResponse(T data, String requestType, String handle) {
        ResponseContext respContext=new ResponseContext();
        respContext.setStatus(BinaryStatus.SUCCESS);
        respContext.setRequestContext(new RequestContext(requestType));
        respContext.setHandle(handle);
        Response<T> response = new Response(data, respContext);
        return response;
    }
}
