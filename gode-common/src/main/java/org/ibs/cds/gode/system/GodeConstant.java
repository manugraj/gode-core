package org.ibs.cds.gode.system;

import java.util.StringJoiner;

public class GodeConstant {
    public static final String ENTITY_BASE_PACKAGE_NAME="org.ibs.cds.gode.entity";
    public static final String CACHE_ENTITY_BASE_PACKAGE_NAME="org.ibs.cds.gode.entity.cache";
    public static final String CACHE_ENTITY_REPO_PACKAGE_NAME="org.ibs.cds.gode.entity.cache.repo";
    public static final String GODE_PROPERTIES="classpath:gode.properties";
    public static final String API_VERSION ="/gode/api/v1";
    public static final String HIDDEN = "(hidden)";

    public StringJoiner url(String data){
        return new StringJoiner("/");
    }
}
