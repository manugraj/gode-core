package org.ibs.cds.gode.swagger;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";
    public static final Contact COMPANY_CONTACT = new Contact("IBS Software", "www.ihavenoidea.com", "some@thing.com");

    private boolean secure;
    @Autowired
    public SwaggerConfig(Environment environment) {
        this.secure = environment.getProperty("gode.security.enabled",Boolean.class,false);
    }

    @Bean
    public Docket api() {
        if(secure){
            return secureDocket();
        }
        return defaultDocket();
    }

    private Docket defaultDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.ibs.cds.gode"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private Docket secureDocket() {
        return defaultDocket()
                    .securitySchemes(new ArrayList<>(Arrays.asList(apiKey())))
                    .securityContexts(Lists.newArrayList(securityContext()));
    }


    private ApiInfo apiInfo() {

        return new ApiInfo(
                "Gode API",
                "APIs for Gode",
                "API TOS",
                "Terms of service",
                COMPANY_CONTACT,
                "", "", Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(
                new SecurityReference("JWT", authorizationScopes));
    }
}
