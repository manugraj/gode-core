package org.ibs.cds.gode.auth.interceptor;

import org.apache.commons.lang3.tuple.Pair;
import org.ibs.cds.gode.auth.manager.AuthManager;
import org.ibs.cds.gode.entity.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class RequestInterceptor extends OncePerRequestFilter {

    private @Autowired
    AuthManager authManager;


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Optional<Pair<String, String>> handleAndToken = authManager.getHandleAndToken(request);
        handleAndToken.ifPresent(pair->{
            if(SecurityContextHolder.getContext().getAuthentication() == null) {
                if (authManager.validateAccessToken(pair.getValue())) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(pair.getKey(), "" , Collections.emptyList());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        });
        chain.doFilter(request, response);
    }
}
