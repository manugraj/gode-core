package org.ibs.cds.gode.system;

import java.util.StringJoiner;

public class GodeConstant {
    public static final String ENTITY_BASE_PACKAGE_NAME="org.ibs.cds.gode.entity";
    public static final String ENTITY_TYPE_PACKAGE_NAME="org.ibs.cds.gode.entity.type";
    public static final String ENTITY_STORE_PACKAGE_NAME="org.ibs.cds.gode.entity.store";
    public static final String ENTITY_CACHE_PACKAGE_NAME="org.ibs.cds.gode.entity.cache";
    public static final String ENTITY_BASE_PACKAGE_ALL="org.ibs.cds.gode.entity.*";
    public static final String GODE_BASE_PACKAGE_NAME="org.ibs.cds.gode";
    public static final String ENTITY_CACHE_REPO_PACKAGE_NAME="org.ibs.cds.gode.entity.cache.repo";
    public static final String ENTITY_STORE_REPO_PACKAGE_NAME="org.ibs.cds.gode.entity.store.repo";
    public static final String GODE_PROPERTIES="classpath:gode.properties";
    public static final String API_VERSION ="/gode/api/v1";
    public static final String HIDDEN = "(hidden)";
    public static final String GODE_API_BASE = "/gode/app";
    public StringJoiner url(String data){
        return new StringJoiner("/");
    }
}
