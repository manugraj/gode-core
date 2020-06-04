package org.ibs.cds.gode.util;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {

    public static <T> Stream<T> from(Iterable<T> iterable, int startIndex , int stopIndex){
        return from(iterable).skip(startIndex).limit(stopIndex - startIndex + 1);
    }

    public static <T> Stream<T> from(Iterable<T> iterable){
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
