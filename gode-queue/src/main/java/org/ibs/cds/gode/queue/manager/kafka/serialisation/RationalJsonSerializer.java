package org.ibs.cds.gode.queue.manager.kafka.serialisation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import org.ibs.cds.gode.exception.KnownException;
@Slf4j
public class RationalJsonSerializer<T> implements Serializer<T> {

    private  final ObjectMapper translator;

    public RationalJsonSerializer() {
        this.translator = new ObjectMapper();
    }

    @Override
    public byte[] serialize(String s, T t) {
        try {
            return translator.writeValueAsString(t).getBytes();
        } catch (Exception exception) {
            log.error("Failed to serialize :{} | {}", s, t);
            throw KnownException.SERILISATION_EXCEPTION.provide(exception);
        }
    }
}
