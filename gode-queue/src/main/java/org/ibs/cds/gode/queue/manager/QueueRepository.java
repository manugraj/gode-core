package org.ibs.cds.gode.queue.manager;

import lombok.Data;
import org.ibs.cds.gode.queue.manager.kafka.KafkaEnabler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Conditional(KafkaEnabler.class)
@Data
public class QueueRepository {
    private final QueueRepo queueRepo;
    private final QueueRepoProperties.PusherProperties pusherProperties;
    private final QueueRepoProperties.SubscriberProperties subscriberProperties;
    private final String queuePrefix;

    @Autowired
    public QueueRepository(QueueRepo queueRepo,
                           QueueRepoProperties.PusherProperties pusherProperties,
                           QueueRepoProperties.SubscriberProperties subscriberProperties,
                           Environment environment) {
        this.queueRepo = queueRepo;
        this.pusherProperties = pusherProperties;
        this.subscriberProperties = subscriberProperties;
        this.queuePrefix = environment.getProperty("gode.queue.context.prefix","gode-");
    }
}
