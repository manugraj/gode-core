package org.ibs.cds.entity.store;

import org.ibs.cds.gode.entity.store.IStoreType;

public enum StoreType implements IStoreType {
    JPA,
    MONGODB,
    CASSANDRA,
    NEO4J
}
