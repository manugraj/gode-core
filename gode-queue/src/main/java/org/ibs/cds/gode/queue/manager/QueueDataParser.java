package org.ibs.cds.gode.queue.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QueueDataParser {

    private ObjectMapper objectMapper;
    public QueueDataParser() {
        objectMapper = new ObjectMapper();
    }

    public <T> String parse(T data) throws JsonProcessingException {
        if(data == null) return null;
        String name = data.getClass().getName();
        try {
            return objectMapper.writeValueAsString(new QueueData(QueueDataType.JSON, name, objectMapper.writeValueAsString(data), null));
        } catch (JsonProcessingException e) {
            return objectMapper.writeValueAsString(new QueueData(QueueDataType.STRING, name, data.toString(), data));
        }
    }

    public <T> T read(String data) throws JsonProcessingException, ClassNotFoundException {
        if(data == null) return null;
        QueueData queueData = objectMapper.readValue(data, QueueData.class);
        if(queueData.getType() == QueueDataType.JSON) return (T) objectMapper.readValue(queueData.getData(), Class.forName(queueData.getClassifer()));
        return (T) queueData.getDump();
    }
}
