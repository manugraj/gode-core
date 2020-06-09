package org.ibs.cds.gode.entity.operation;

import org.ibs.cds.gode.exception.Error;
import org.ibs.cds.gode.status.BinaryStatus;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;
import org.ibs.cds.gode.web.context.RequestContext;
import org.ibs.cds.gode.web.context.ResponseContext;

public class Processor {

    public static <T> Response<T> successResponse(T data, Request<?> request, String urlOrHandle) {
        ResponseContext respContext=new ResponseContext();
        respContext.setStatus(BinaryStatus.SUCCESS);
        respContext.setRequestContext(request.getContext());
        respContext.setHandle(urlOrHandle);
        Response<T> response = new Response(data, respContext);
        return response;
    }

    public static <T> Response<T> failureResponse(Request<?> request, Error error, String urlOrHandle) {
        ResponseContext respContext=new ResponseContext();
        respContext.setStatus(BinaryStatus.FAILURE);
        respContext.setRequestContext(request.getContext());
        respContext.setError(error);
        respContext.setHandle(urlOrHandle);
        Response response = new Response();
        response.setContext(respContext);
        return response;
    }

    public static <T> Response<T> successResponse(T data, String requestType, String urlOrHandle) {
        ResponseContext respContext=new ResponseContext();
        respContext.setStatus(BinaryStatus.SUCCESS);
        respContext.setRequestContext(new RequestContext(requestType));
        respContext.setHandle(urlOrHandle);
        Response<T> response = new Response(data, respContext);
        return response;
    }
}
