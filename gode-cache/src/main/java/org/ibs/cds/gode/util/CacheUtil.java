package org.ibs.cds.gode.util;

public class CacheUtil {
    public static String getCacheName(String entityName) {
        return entityName.concat("cache").toLowerCase();
    }
}
