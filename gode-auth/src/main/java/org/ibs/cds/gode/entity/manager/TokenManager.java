package org.ibs.cds.gode.entity.manager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.auth.config.SecurityConfiguration;
import org.ibs.cds.gode.entity.repo.TokenRepo;
import org.ibs.cds.gode.entity.repo.TokenRepository;
import org.ibs.cds.gode.entity.type.Token;
import org.ibs.cds.gode.entity.type.TokenData;
import org.ibs.cds.gode.exception.KnownException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;

@Service
public class TokenManager extends CachedStateEntityManager<Token, Token, String, TokenRepository> {

    private Integer tokenExpiryPeriod;
    private String passwordSalt;
    private Integer refreshTokenExpiryPeriod;

    @Autowired
    public TokenManager(TokenRepository repo, Environment environment) {
        super(repo);
        this.tokenExpiryPeriod = environment.getProperty("gode.security.token.access.expiry", Integer.class);
        this.refreshTokenExpiryPeriod = environment.getProperty("gode.security.token.refresh.expiry", Integer.class);
        this.passwordSalt = environment.getProperty("gode.security.token.salt");
    }

    public String extractHandle(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String newHandle(){
        return UUID.randomUUID().toString();
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(passwordSalt).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Token generateToken() {
        return createToken(new HashMap<>(), newHandle(), tokenExpiryPeriod, refreshTokenExpiryPeriod);
    }

    public Token generateToken(long validPeriod) {
        return createToken(new HashMap<>(), newHandle(), validPeriod, refreshTokenExpiryPeriod);
    }

    private Token createToken(Map<String, Object> claims, String subject, long expiryPeriod, long refreshTokenExpiryPeriod) {
        Date issuedAt = new Date(System.currentTimeMillis());
        TokenData accessToken = buildToken(claims, subject, expiryPeriod, issuedAt);
        TokenData refreshToken = buildToken(claims, subject, refreshTokenExpiryPeriod, issuedAt);
        return recordNewToken(subject, accessToken, refreshToken);
    }

    private TokenData updateAccessToken(Map<String, Object> claims, String subject, long expiryPeriod) {
        Date issuedAt = new Date(System.currentTimeMillis());
        TokenData accessToken = buildToken(claims, subject, expiryPeriod, issuedAt);
        updateAccessToken(subject, accessToken);
        return accessToken;
    }

    @NotNull
    private TokenData buildToken(Map<String, Object> claims, String subject, long refreshTokenExpiryPeriod, Date issuedAt) {
        String refreshTokenString = Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(issuedAt)
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpiryPeriod))
                .signWith(SignatureAlgorithm.HS256, passwordSalt).compact();
        TokenData refreshToken = new TokenData();
        refreshToken.setIssuedTime(issuedAt);
        refreshToken.setTokenString(refreshTokenString);
        refreshToken.setValidityPeriod(refreshTokenExpiryPeriod);
        return refreshToken;
    }

    @NotNull
    private Token recordNewToken(String subject, TokenData accessToken, TokenData refreshToken) {
        Token token = new Token();
        token.setHandle(subject);
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        this.save(token);
        return token;
    }

    private void updateAccessToken(String subject, TokenData accessToken) {
        Token token = this.find(subject);
        if(token != null){
            token.setAccessToken(accessToken);
            this.save(token);
            return;
        }
        throw KnownException.AUTHORISATION_EXCEPTION.provide( "Unknown token");
    }

    public TokenData refreshToken(String token){
        if(validateRefreshToken(token)){
            String username = extractHandle(token);
            return updateAccessToken(new HashMap<>(), username, tokenExpiryPeriod);
        }
        throw KnownException.AUTHORISATION_EXCEPTION.provide("Token is invalid");
    }

    public boolean validateAccessToken(String token) {
        try {
            String handle = extractHandle(token);
            Token tokenData = this.find(handle);
            return (tokenData.getAccessToken().getTokenString().equals(token))
                    && !isTokenExpired(token);
        } catch(Exception e) {
            throw KnownException.AUTHORISATION_EXCEPTION.provide("Invalid access token");
        }
    }

    public boolean validateRefreshToken(String token) {
        String handle = extractHandle(token);
        Token tokenData = this.find(handle);
        return (tokenData.getRefreshToken().getTokenString().equals(token)) && !isTokenExpired(token) ;
    }


    public Optional<Pair<String, String>> getHandleAndToken(HttpServletRequest request){
        final String authorizationHeader = request.getHeader(SecurityConfiguration.SECURE_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(SecurityConfiguration.SECURE_TOKEN_PREFIX)) {
            String token = authorizationHeader.substring(7);
            String handle = this.extractHandle(token);
            return Optional.of(Pair.of(handle, token));
        }
        return Optional.empty();
    }


    @Override
    public Token transformEntity(Token entity) {
        return entity;
    }

    @Override
    public Token transformView(Token entity) {
        return entity;
    }
}
