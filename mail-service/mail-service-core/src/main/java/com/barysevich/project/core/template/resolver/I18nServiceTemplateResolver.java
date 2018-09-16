package com.barysevich.project.core.template.resolver;


import com.barysevich.project.api.TemplateService;
import com.barysevich.project.api.common.GetTemplateError;
import com.barysevich.project.api.request.GetTemplateRequest;
import com.barysevich.project.localization.Locale;
import com.barysevich.project.result.Result;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import java.util.Map;


public class I18nServiceTemplateResolver extends StringTemplateResolver
{
    private static final Logger logger = LoggerFactory.getLogger(I18nServiceTemplateResolver.class);

    private final static String PREFIX = "";

    private final TemplateService templateService;


    public I18nServiceTemplateResolver(final TemplateService templateService)
    {
        this.templateService = templateService;
        setResolvablePatterns(Sets.newHashSet(PREFIX + "*"));
    }


    @Override
    protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration,
                                                        String ownerTemplate,
                                                        String template,
                                                        Map<String, Object> templateResolutionAttributes)
    {

        // ThymeleafTemplate is our internal object that contains the content.
        // You should change this to match you're set up.

        final GetTemplateRequest getTemplateRequest = new GetTemplateRequest(
                template,
                new Locale("EN")); // TODO get locale from template context

        final Result<String, GetTemplateError> templateResult = templateService.getTemplateByName(getTemplateRequest);

        if (!templateResult.isSuccess())
        {
            logger.info("Resolve template failed : getTemplateRequest={}, error={}",
                getTemplateRequest, templateResult.getErrorCode());
            return null;
        }

        return super.computeTemplateResource(configuration, ownerTemplate, templateResult.getResult(), templateResolutionAttributes);
    }
}
