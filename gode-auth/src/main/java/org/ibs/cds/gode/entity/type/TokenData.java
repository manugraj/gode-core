package org.ibs.cds.gode.entity.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ibs.cds.gode.system.GodeAppEnvt;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenData {
    private String tokenString;
    private Date issuedTime;
    private Long validityPeriod;

    @Override
    public String toString() {
        return "TokenData[" +
                "tokenString=" + GodeAppEnvt.HIDDEN +
                ", issuedTime=" + issuedTime +
                ", validityPeriod=" + validityPeriod +
                ']';
    }
}
