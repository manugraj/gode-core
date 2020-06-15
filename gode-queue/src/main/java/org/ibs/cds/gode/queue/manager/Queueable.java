package org.ibs.cds.gode.queue.manager;

import org.ibs.cds.gode.queue.manager.kafka.serialisation.RationalJsonDeserializer;

public interface Queueable {
    String context();
    Class<? extends RationalJsonDeserializer<? extends Queueable>> translator();
}
