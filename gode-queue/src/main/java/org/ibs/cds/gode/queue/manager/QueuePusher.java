package org.ibs.cds.gode.queue.manager;

public interface QueuePusher<V,T extends QueueRepositoryProperties.PusherProperties> {
    boolean init(T properties);
    boolean send(String context, Object message);
    boolean send(Queueable message);
}
