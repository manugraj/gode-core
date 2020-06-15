package org.ibs.cds.gode.queue.manager;

import org.ibs.cds.gode.queue.manager.kafka.serialisation.RationalJsonDeserializer;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QueueRepository<PusherProperties extends QueueRepositoryProperties.PusherProperties,SubscriberProperties extends QueueRepositoryProperties.SubscriberProperties> {

    QueuePusher pusher(PusherProperties properties);
    QueueSubscriber consumer(SubscriberProperties properties, Class<? extends RationalJsonDeserializer> classType);
}
