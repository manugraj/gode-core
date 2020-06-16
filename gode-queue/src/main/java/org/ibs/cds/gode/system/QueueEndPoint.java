package org.ibs.cds.gode.system;

import org.ibs.cds.gode.queue.manager.kafka.KafkaEnabler;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@CrossOrigin
@RestController
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value= "/gode/queue", produces = "application/json;charset=UTF-8")
@Conditional(KafkaEnabler.class)
public @interface QueueEndPoint {
}
