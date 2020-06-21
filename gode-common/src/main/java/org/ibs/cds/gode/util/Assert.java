package org.ibs.cds.gode.util;

import org.ibs.cds.gode.exception.KnownException;

import java.util.Arrays;
import java.util.Objects;

public class Assert {

    public static void notNull(Object obj){
        if(obj == null) throw KnownException.ASSERTION_FAILED.provide("Value is null");
    }

    public static void notNull(String message, Object... obj){
        if(obj == null || Arrays.stream(obj).anyMatch(Objects::isNull)) throw KnownException.ASSERTION_FAILED.provide(message);
    }

    public static void notNull(String message, Object obj){
        if(obj == null) throw KnownException.ASSERTION_FAILED.provide(message);
    }

    public static void notNull(String message, Object obj1, Object obj2){
        if(obj1 == null || obj2 == null) throw KnownException.ASSERTION_FAILED.provide(message);
    }
}
