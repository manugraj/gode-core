package org.ibs.cds.gode.queue.manager;

import java.util.Optional;
import java.util.function.Consumer;

public interface QueueSubscriber<V,T extends QueueRepoProperties.SubscriberProperties> {
    boolean init(T properties);
    void subscribe(String context, int pollInterval, Consumer<Optional<V>> consumer);
    void subscribe(String context, Consumer<Optional<V>> consumer);
}
