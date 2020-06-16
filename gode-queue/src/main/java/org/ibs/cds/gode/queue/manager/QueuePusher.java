package org.ibs.cds.gode.queue.manager;

import org.ibs.cds.gode.exception.GodeQueuePushFailedException;

public interface QueuePusher<V,T extends QueueRepoProperties.PusherProperties> {
    boolean init(T properties);
    boolean send(String context, V message) throws GodeQueuePushFailedException;
    boolean send(Queueable message);
}
