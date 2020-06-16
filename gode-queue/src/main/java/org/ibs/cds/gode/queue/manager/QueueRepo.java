package org.ibs.cds.gode.queue.manager;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QueueRepo<PusherProperties extends QueueRepoProperties.PusherProperties,SubscriberProperties extends QueueRepoProperties.SubscriberProperties> {

    QueuePusher pusher(PusherProperties properties);
    QueueSubscriber consumer(SubscriberProperties properties);
}
