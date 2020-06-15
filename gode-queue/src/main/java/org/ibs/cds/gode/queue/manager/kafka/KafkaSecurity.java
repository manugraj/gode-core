package org.ibs.cds.gode.queue.manager.kafka;

import lombok.Data;

@Data
public class KafkaSecurity {
    private boolean sasl;
    private String mechanism;
    private String jaas;
}
