package org.ibs.cds.gode.queue.manager.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.ibs.cds.gode.exception.Error;
import org.ibs.cds.gode.exception.GodeQueuePushFailedException;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.queue.manager.QueuePusher;
import org.ibs.cds.gode.queue.manager.Queueable;
import org.ibs.cds.gode.util.Assert;
import org.ibs.cds.gode.util.Promise;

@Slf4j
public class KafkaPusher<T> implements QueuePusher<T,KafkaInitialiseProperties>{

    private KafkaProducer<String, T> kafkaPusher;

    @Override
    public boolean init(KafkaInitialiseProperties kafkaPusherProperties) {
        kafkaPusher = new KafkaProducer(kafkaPusherProperties.pusherProperties());
        return true;
    }


    @Override
    public boolean send(String context, Object message) {
        Assert.notNull("Kafka queue pusher/message/context cannot be null", kafkaPusher, context, message);
        return sendData(context, message);
    }

    private boolean sendData(String context, Object message){
        return !(new Promise(kafkaPusher.send(new ProducerRecord(context, message))).whenComplete((s,e)->{
            if(e instanceof  Throwable && e != null){
                log.error("Queue push failed for {}", message);
                throw KnownException.QUEUE_PUSH_FAILED_EXCEPTION.provide((Throwable)e);
            }
        }).isCompletedExceptionally());
    }

    @Override
    public boolean send(Queueable message) {
        Assert.notNull("Kafka queue pusher/queueable message/context cannot be null", kafkaPusher, message, message.context());
        return sendData(message.context(), message);
    }

}
