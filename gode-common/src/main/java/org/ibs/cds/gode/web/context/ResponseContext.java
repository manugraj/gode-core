package org.ibs.cds.gode.web.context;

import lombok.Getter;
import lombok.Setter;

public class ResponseContext implements WebMessageContext<String> {

    private @Setter String handle;
    private @Setter @Getter RequestContext requestContext;

    @Override
    public String getHandle() {
        return handle;
    }
}
