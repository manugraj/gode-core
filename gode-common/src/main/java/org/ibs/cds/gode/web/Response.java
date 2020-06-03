package org.ibs.cds.gode.web;

import lombok.Setter;
import org.ibs.cds.gode.web.context.ResponseContext;

public class Response<Data> implements WebMessage<Data, ResponseContext> {

    private @Setter Data data;
    private @Setter ResponseContext context;

    @Override
    public Data getData() {
        return data;
    }

    @Override
    public ResponseContext getContext() {
        return context;
    }
}
