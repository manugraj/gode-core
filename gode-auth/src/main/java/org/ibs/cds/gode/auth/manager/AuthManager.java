package org.ibs.cds.gode.auth.manager;

import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.entity.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class AuthManager {

    private final TokenManager tokenManager;

    @Autowired
    public AuthManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public Optional<Pair<String, String>> getHandleAndToken(HttpServletRequest request){
        return this.tokenManager.getHandleAndToken(request);
    }

    public boolean validateAccessToken(String value) {
        return this.tokenManager.validateAccessToken(value);
    }
}
