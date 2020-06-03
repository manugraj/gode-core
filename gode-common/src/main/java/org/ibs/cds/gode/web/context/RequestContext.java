package org.ibs.cds.gode.web.context;

import lombok.Setter;

public class RequestContext implements WebMessageContext<String> {

    private @Setter String handle;

    @Override
    public String getHandle() {
        return handle;
    }
}
