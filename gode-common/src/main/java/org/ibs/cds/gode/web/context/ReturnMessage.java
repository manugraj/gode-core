package org.ibs.cds.gode.web.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.exception.Error;
import org.ibs.cds.gode.status.BinaryStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnMessage implements WebMessageContext<String>  {
    private String handle;
    private BinaryStatus status;
    private Error error;
    private RequestContext requestContext;
}
