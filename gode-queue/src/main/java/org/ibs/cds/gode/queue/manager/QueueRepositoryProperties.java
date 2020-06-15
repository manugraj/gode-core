package org.ibs.cds.gode.queue.manager;

import org.ibs.cds.gode.queue.manager.kafka.serialisation.RationalJsonDeserializer;

import java.util.Properties;

public interface QueueRepositoryProperties {

    interface PusherProperties extends QueueRepositoryProperties{
        Properties pusherProperties();
    }

    interface SubscriberProperties extends QueueRepositoryProperties{
        Properties subscriberProperties(Class<? extends RationalJsonDeserializer> deserialiser);
        int getPollInterval();
    }

}
