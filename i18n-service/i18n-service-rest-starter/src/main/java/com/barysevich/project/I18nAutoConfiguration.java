package com.barysevich.project;


import com.barysevich.project.api.TemplateService;
import com.barysevich.project.api.TemplateServiceFeign;
import com.barysevich.project.feign.FeignServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class I18nAutoConfiguration
{
    private final FeignServiceFactory feignFactory;

    @Value("${services.i18n.url}")
    private String url;
//    private String url = "http://localhost:8001";


    @Autowired
    public I18nAutoConfiguration(final FeignServiceFactory feignFactory)
    {
        this.feignFactory = feignFactory;
    }


    @Bean
    @ConditionalOnMissingBean(TemplateService.class)
    public TemplateService templateService()
    {
        return feignFactory.build(TemplateServiceFeign.class, url);
    }
}
