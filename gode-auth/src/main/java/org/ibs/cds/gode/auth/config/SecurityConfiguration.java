package org.ibs.cds.gode.auth.config;

import org.ibs.cds.gode.auth.interceptor.AuthEntryPoint;
import org.ibs.cds.gode.auth.interceptor.CustomAccessDeniedHandler;
import org.ibs.cds.gode.auth.interceptor.RequestInterceptor;
import org.ibs.cds.gode.system.GodeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@ComponentScan(basePackages = "org.ibs.cds.gode.auth")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String SECURE_HEADER = "Authorization";
    public static final String SECURE_TOKEN_PREFIX = "Bearer ";

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            GodeConstant.API_VERSION.concat("/").concat("auth"),
            GodeConstant.API_VERSION.concat("/").concat("auth/refresh"),
            GodeConstant.API_VERSION.concat("/").concat("auth/invalidate"),
            "/graphiql"
    };
    private static final String[] UI_ASSETS = {"/",
            "/*.html",
            "/favicon.ico",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"};

    @Autowired
    private RequestInterceptor requestInterceptor;
    @Autowired
    private AuthEntryPoint authEntryPoint;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and()
                .addFilterBefore(requestInterceptor, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(UI_ASSETS);
    }
}
