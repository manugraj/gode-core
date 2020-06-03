package org.ibs.cds.gode.web;

import org.ibs.cds.gode.web.context.WebMessageContext;

public interface WebMessage<Data, Context extends WebMessageContext<?>> {
    Data getData();
    Context getContext();
}
