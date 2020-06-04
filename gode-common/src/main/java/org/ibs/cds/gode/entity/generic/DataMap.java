package org.ibs.cds.gode.entity.generic;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;

@NoArgsConstructor
public class DataMap extends LinkedHashMap<Serializable, Serializable> {

    public DataMap(Serializable attributeName, Serializable attributeValue) {
        add(attributeName, attributeValue);
    }

    public DataMap add(Serializable attributeName, Serializable attributeValue) {
        put(attributeName, attributeValue);
        return this;
    }

    public static DataMap empty(){
        return new DataMap();
    }
}
