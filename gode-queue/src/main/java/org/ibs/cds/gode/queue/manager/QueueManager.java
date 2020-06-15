package org.ibs.cds.gode.queue.manager;

import org.ibs.cds.gode.queue.manager.kafka.serialisation.RationalJsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.function.Consumer;

public class QueueManager<T> {

    private QueuePusher<T,?> pusher;
    private QueueSubscriber<T, ?> consumer;
    private String context;
    private QueueRepositoryProperties.PusherProperties pusherProperties;
    private QueueRepositoryProperties.SubscriberProperties subscriberProperties;

    private QueueManager(String context, Class<? extends RationalJsonDeserializer<T>> classType, QueueRepository queueRepository,
                        QueueRepositoryProperties.PusherProperties pusherProperties,
                        QueueRepositoryProperties.SubscriberProperties subscriberProperties){
        this.pusher = queueRepository.pusher(pusherProperties);
        this.consumer = queueRepository.consumer(subscriberProperties, classType);
        this.context = context;
        this.pusherProperties = pusherProperties;
        this.subscriberProperties = subscriberProperties;
    }

    public static <T> QueueManager<T> start(String context, Class<? extends RationalJsonDeserializer<T>> deserializerClass, QueueRepository queueRepository,
                                         QueueRepositoryProperties.PusherProperties pusherProperties,
                                         QueueRepositoryProperties.SubscriberProperties subscriberProperties){
        return new QueueManager(context, deserializerClass, queueRepository, pusherProperties, subscriberProperties);
    }

    public static <T extends Queueable> QueueManager<T> startAndPush(T queueable, QueueRepository queueRepository,
                                         QueueRepositoryProperties.PusherProperties pusherProperties,
                                         QueueRepositoryProperties.SubscriberProperties subscriberProperties){
        QueueManager manager = new QueueManager(queueable.context(), queueable.translator(), queueRepository, pusherProperties, subscriberProperties);
        manager.push(queueable);
        return manager;
    }

    public boolean push(T... data){
        return Arrays.stream(data).map(d ->pusher.send(context, d)).allMatch(k->k);
    }

    public boolean push(T data){
        return this.pusher.send(context, data);
    }

    public boolean subscribe(Consumer<T> consumptionFunction){
        this.consumer.subscribe(context, subscriberProperties.getPollInterval(), consumptionFunction);
        return true;
    }

}
