package org.ibs.cds.gode.queue.manager.kafka.serialisation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.ibs.cds.gode.exception.KnownException;

@Slf4j
public class RationalJsonDeserializer<T> implements Deserializer<T> {

	private  final ObjectMapper translator;
	private Class<T> classType;

	public RationalJsonDeserializer(Class<T> classType) {
		this.classType = classType;
		this.translator = new ObjectMapper();
	}

	public RationalJsonDeserializer() {
		this.translator = new ObjectMapper();
	}

	@Override
	public T deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(data, classType);
		} catch (Exception exception) {
			log.error("Failed to deserialize object: {} from topic:{}", data,topic);
			throw KnownException.SERILISATION_EXCEPTION.provide(exception);
		}
	}

}
