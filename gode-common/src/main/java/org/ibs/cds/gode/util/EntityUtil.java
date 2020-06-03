package org.ibs.cds.gode.util;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class EntityUtil {

    public static int hashCode(Object object){
        return HashCodeBuilder.reflectionHashCode(object);
    }

    public static String toString(Object object){
        return ToStringBuilder.reflectionToString(object);
    }
}
