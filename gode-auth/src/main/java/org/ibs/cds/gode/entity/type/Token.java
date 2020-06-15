package org.ibs.cds.gode.entity.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.entity.cache.CacheEntity;
import org.ibs.cds.gode.entity.cache.CacheType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Token extends CacheEntity<String> {
    private String handle;
    private TokenData accessToken;
    private TokenData refreshToken;

    @Override
    public String getId() {
        return this.handle;
    }

    @Override
    public void setId(String handle) {
        this.handle = handle;
    }

    @Override
    public CacheType getStoreType() {
        return CacheType.IGNITE;
    }
}
