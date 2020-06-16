package org.ibs.cds.gode.queue.manager.kafka;

import org.ibs.cds.gode.queue.manager.QueueRepo;
import org.ibs.cds.gode.system.GodeConstant;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;


@Conditional(KafkaEnabler.class)
@Configuration
@ComponentScan(basePackages = GodeConstant.GODE_BASE_PACKAGE_NAME)
public class KafkaQueueRepo implements QueueRepo<KafkaProperties, KafkaProperties> {


    @Override
    public KafkaPusher pusher(KafkaProperties properties) {
        KafkaPusher pusher = new KafkaPusher();
        pusher.init(properties);
        return pusher;
    }

    public KafkaSubscriber consumer(KafkaProperties properties){
        KafkaSubscriber consumer = new KafkaSubscriber();
        consumer.init(properties);
        return consumer;
    }
}
