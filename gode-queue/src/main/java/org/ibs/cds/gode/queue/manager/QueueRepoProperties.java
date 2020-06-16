package org.ibs.cds.gode.queue.manager;

import java.util.Properties;

public interface QueueRepoProperties {

    interface PusherProperties extends QueueRepoProperties {
        Properties pusherProperties();
    }

    interface SubscriberProperties extends QueueRepoProperties {
        Properties subscriberProperties();
        int getPollInterval();
    }

}
