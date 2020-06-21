package org.ibs.cds.gode.system;

import java.util.UUID;

public class AppId {

    public static Long next(){
        return UUID.randomUUID().getLeastSignificantBits();
    }
}
