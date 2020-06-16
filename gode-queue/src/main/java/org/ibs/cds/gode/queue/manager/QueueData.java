package org.ibs.cds.gode.queue.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueueData {
    private QueueDataType type;
    private String classifer;
    private String data;
    private Object dump;
}
