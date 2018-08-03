package com.barysevich.authorization;

import com.barysevich.authorization.api.AuthorizationService;
import com.barysevich.authorization.api.AuthorizationServiceFeign;
import com.barysevich.project.feign.FeignServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationAutoConfiguration
{
    private final FeignServiceFactory feignFactory;

    @Value("${services.auth.url}")
    private String url;


    @Autowired
    public AuthorizationAutoConfiguration(final FeignServiceFactory feignFactory)
    {
        this.feignFactory = feignFactory;
    }


    @Bean
    @ConditionalOnMissingBean(AuthorizationService.class)
    public AuthorizationService authorizationService()
    {
        return feignFactory.build(AuthorizationServiceFeign.class, url);
    }
}
