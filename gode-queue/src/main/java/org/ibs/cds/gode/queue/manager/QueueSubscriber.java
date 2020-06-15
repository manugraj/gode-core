package org.ibs.cds.gode.queue.manager;

import org.ibs.cds.gode.queue.manager.kafka.serialisation.RationalJsonDeserializer;

import java.util.function.Consumer;

public interface QueueSubscriber<V,T extends QueueRepositoryProperties.SubscriberProperties> {
    boolean init(T properties, Class<? extends RationalJsonDeserializer<V>> classType);
    void subscribe(String context, int pollInterval, Consumer<V> consumer);
    void subscribe(String context, Consumer<V> consumer);
}
