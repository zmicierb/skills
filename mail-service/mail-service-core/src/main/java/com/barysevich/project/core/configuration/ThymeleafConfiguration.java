package com.barysevich.project.core.configuration;


import com.barysevich.project.api.TemplateService;
import com.barysevich.project.core.template.resolver.I18nServiceTemplateResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Set;


@Configuration
public class ThymeleafConfiguration
{
    @Bean
    public TemplateEngine templateEngine(final Set<ITemplateResolver> templateResolvers)
    {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();

//        templateEngine.setTemplateResolvers(templateResolvers);

        return templateEngine;
    }


    @Bean
    public ITemplateResolver springResourceTemplateResolver()
    {
        final SpringResourceTemplateResolver springResourceTemplateResolver = new SpringResourceTemplateResolver();
//        springResourceTemplateResolver.setPrefix("classpath:/templates/");
//        springResourceTemplateResolver.setOrder(Integer.MAX_VALUE);
//        springResourceTemplateResolver.setSuffix(".html");
//        springResourceTemplateResolver.setCacheable(false);

        return springResourceTemplateResolver;
    }


    @Bean
    public ITemplateResolver i18nServiceTemplateResolver(final TemplateService templateService)
    {
        final I18nServiceTemplateResolver i18nServiceTemplateResolver =
            new I18nServiceTemplateResolver(templateService);
        i18nServiceTemplateResolver.setOrder(Integer.MAX_VALUE - 1);

        return i18nServiceTemplateResolver;
    }
}
