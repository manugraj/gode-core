package org.ibs.cds.gode.queue.manager.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.queue.manager.QueueDataParser;
import org.ibs.cds.gode.queue.manager.QueuePusher;
import org.ibs.cds.gode.queue.manager.Queueable;
import org.ibs.cds.gode.util.Assert;
import org.ibs.cds.gode.util.Promise;

@Slf4j
public class KafkaPusher<T> implements QueuePusher<T, KafkaProperties>{

    private KafkaProducer<String, T> kafkaPusher;
    private QueueDataParser parser;

    @Override
    public boolean init(KafkaProperties kafkaPusherProperties) {
        kafkaPusher = new KafkaProducer(kafkaPusherProperties.pusherProperties());
        parser = new QueueDataParser();
        return true;
    }


    @Override
    public boolean send(String context, T message) {
        try {
            Assert.notNull("Kafka queue pusher/message/context cannot be null", kafkaPusher, context, message);
            return sendData(context, message);
        } catch (JsonProcessingException e) {
            throw KnownException.QUEUE_PUSH_FAILED_EXCEPTION.provide(e);
        }
    }

    private boolean sendData(String context, Object message) throws JsonProcessingException {
        return !(new Promise(kafkaPusher.send(new ProducerRecord(context, parser.parse(message)))).whenComplete((s,e)->{
            if(e instanceof  Throwable && e != null){
                log.error("Queue push failed for {}", message);
                throw KnownException.QUEUE_PUSH_FAILED_EXCEPTION.provide((Throwable)e);
            }
        }).isCompletedExceptionally());
    }

    @Override
    public boolean send(Queueable message) {
        try {
            Assert.notNull("Kafka queue pusher/queueable message/context cannot be null", kafkaPusher, message, message.context());
            return sendData(message.context(), message);
        } catch (JsonProcessingException e) {
            throw KnownException.QUEUE_PUSH_FAILED_EXCEPTION.provide(e);
        }
    }

}
