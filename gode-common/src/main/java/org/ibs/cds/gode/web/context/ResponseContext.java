package org.ibs.cds.gode.web.context;

import lombok.*;
import org.ibs.cds.gode.entity.operation.QueryContext;
import org.ibs.cds.gode.exception.Error;
import org.ibs.cds.gode.status.BinaryStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseContext implements WebMessageContext<String> {

    private String handle;
    private BinaryStatus status;
    private Error error;
    private RequestContext requestContext;

}
