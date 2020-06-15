package org.ibs.cds.gode.queue.manager.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.ibs.cds.gode.exception.KnownException;
import org.ibs.cds.gode.queue.manager.QueueSubscriber;
import org.ibs.cds.gode.queue.manager.kafka.serialisation.RationalJsonDeserializer;
import org.ibs.cds.gode.util.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Slf4j
public class KafkaSubscriber<T> implements QueueSubscriber<T, KafkaInitialiseProperties> {

    private KafkaConsumer<String, T> consumer;

    @Override
    public boolean init(KafkaInitialiseProperties properties, Class<? extends RationalJsonDeserializer<T>> classType) {
        consumer = new KafkaConsumer(properties.subscriberProperties(classType));
        return false;
    }

    @Override
    public void subscribe(String context, int pollInterval, Consumer<T> consumptionFunction) {
        Assert.notNull("Subscriber is not initialised", consumer);
        consumer.subscribe(List.of(context));
        CompletableFuture
                .runAsync(() -> this.startSubscription(pollInterval == 0 ? 60 : pollInterval, consumptionFunction))
                .whenComplete((s,e)->{
                    if(e != null){
                        log.error("Error thrown while subscription: {}", ExceptionUtils.getStackTrace(e));
                    }
                });
        log.info("Subscription for {} started", context);
    }

    @Override
    public void subscribe(String topic, Consumer<T> consumptionFunction) {
        subscribe(topic, 60, consumptionFunction);
    }

    private void startSubscription(int pollInterval, Consumer<T> consumptionFunction) {
        while (true) {
            consumption(pollInterval, consumptionFunction);
        }
    }

    private void consumption(int pollInterval, Consumer<T> consumptionFunction) {
        try {
            ConsumerRecords<String, T> records = consumer.poll(Duration.ofSeconds(pollInterval));
            log.debug("Poll done for queue, received count:{}", records.count());
            for (ConsumerRecord<String, T> record : records) {
                log.debug("Consuming record from queue: offset = {}, value = {}", record.offset(), record.value());
                consumptionFunction.accept(record.value());
                log.debug("Consumption of record complete");
            }
            consumer.commitSync();
        } catch (Throwable e) {
            log.error("Consumption of record failed");
            throw KnownException.QUEUE_CONSUMPTION_FAILED_EXCEPTION.provide(e);
        }
    }
}
