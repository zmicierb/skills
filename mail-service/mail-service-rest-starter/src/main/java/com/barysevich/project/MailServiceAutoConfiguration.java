package com.barysevich.project;


//import com.barysevich.project.api.MailService;
//import com.barysevich.project.api.MailServiceFeign;
import com.barysevich.project.feign.FeignServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MailServiceAutoConfiguration
{
    private final FeignServiceFactory feignFactory;

    @Value("${services.mail.url}")
    private String url;


    @Autowired
    public MailServiceAutoConfiguration(final FeignServiceFactory feignFactory)
    {
        this.feignFactory = feignFactory;
    }


//    @Bean
//    @ConditionalOnMissingBean(MailService.class)
//    public MailService templateService()
//    {
//        return feignFactory.build(MailServiceFeign.class, url);
//    }
}
