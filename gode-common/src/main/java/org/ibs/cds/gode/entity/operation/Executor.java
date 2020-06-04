package org.ibs.cds.gode.entity.operation;

import com.querydsl.core.types.Predicate;
import org.ibs.cds.gode.exception.GodeException;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.pagination.PageContext;
import org.ibs.cds.gode.util.Assert;
import org.ibs.cds.gode.web.Request;
import org.ibs.cds.gode.web.Response;

import java.util.function.Function;

public class Executor {

    public static <RequestType, Manager, ResponseType> Response<ResponseType> run(Function<RequestType, Function<Manager,ResponseType>> logic,
                                                                                  Request<RequestType> request,
                                                                                  Manager manager,
                                                                                  KnownException error,
                                                                                  String handle){
        Assert.notNull("Request processing essentials are unavailable", logic, request, error);
        try {
            return Processor.successResponse(logic.apply(request.getData()).apply(manager), request, handle);
        }catch (GodeException e){
            throw e;
        }catch (Throwable e){
            throw error.provide(e);
        }
    }

    public static <Manager, ResponseType> Response<ResponseType> run(Function<PageContext,Function<Predicate, Function<Manager,ResponseType>>> logic,
                                                                                  PageContext ctx, Predicate predicate,
                                                                                  Manager manager,
                                                                                  KnownException error,
                                                                                  String handle){
        Assert.notNull("Request processing essentials are unavailable", logic, error);
        try {
            return Processor.successResponse(logic.apply(ctx).apply(predicate).apply(manager), "QueryByCriteria", handle);
        }catch (GodeException e){
            throw e;
        }catch (Throwable e){
            throw error.provide(e);
        }
    }

}
