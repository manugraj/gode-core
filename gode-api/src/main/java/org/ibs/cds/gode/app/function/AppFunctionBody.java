package org.ibs.cds.gode.app.function;

public abstract class AppFunctionBody<Request, Manager, Response>  {

    public abstract Response process(Request request, Manager manager);

}
