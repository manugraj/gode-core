package org.ibs.cds.gode.util;

public class CacheUtil {
    public static String getCacheName(String entityName) {
        Assert.notNull("Cache name cannot be found without entityName" ,entityName);
        return entityName.concat("cache").toLowerCase();
    }
}
