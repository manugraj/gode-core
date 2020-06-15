package org.ibs.cds.gode.queue.manager.kafka;

import org.ibs.cds.gode.queue.manager.QueueRepository;
import org.ibs.cds.gode.queue.manager.QueueSubscriber;
import org.ibs.cds.gode.queue.manager.kafka.serialisation.RationalJsonDeserializer;
import org.ibs.cds.gode.system.GodeConstant;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;


@Conditional(KafkaEnabler.class)
@Repository
@ComponentScan(basePackages = GodeConstant.GODE_BASE_PACKAGE_NAME)
public class KafkaQueueRepository implements QueueRepository<KafkaInitialiseProperties, KafkaInitialiseProperties> {


    @Override
    public KafkaPusher pusher(KafkaInitialiseProperties properties) {
        KafkaPusher pusher = new KafkaPusher();
        pusher.init(properties);
        return pusher;
    }

    public KafkaSubscriber consumer(KafkaInitialiseProperties properties, Class<? extends RationalJsonDeserializer> classType ){
        KafkaSubscriber consumer = new KafkaSubscriber();
        consumer.init(properties, classType);
        return consumer;
    }
}
