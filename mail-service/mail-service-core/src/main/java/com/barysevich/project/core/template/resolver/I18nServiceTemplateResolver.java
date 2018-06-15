package com.barysevich.project.core.template.resolver;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.barysevich.project.api.TemplateService;
import com.barysevich.project.api.common.GetTemplateError;
import com.barysevich.project.api.request.GetTemplateRequest;
import com.barysevich.project.localization.Locale;
import com.barysevich.project.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.templatemode.StandardTemplateModeHandlers;
import org.thymeleaf.templateresolver.ITemplateResolutionValidity;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.NonCacheableTemplateResolutionValidity;
import org.thymeleaf.templateresolver.TemplateResolution;
import org.thymeleaf.util.Validate;


public class I18nServiceTemplateResolver implements ITemplateResolver
{
    private static final String NAME = "LocalizationHTTPTemplateResolver";

    private static final Logger logger = LoggerFactory.getLogger(I18nServiceTemplateResolver.class);

    private final String templateMode;

    private final TemplateService templateService;

    private Integer order = 1;


    public I18nServiceTemplateResolver(final TemplateService templateService)
    {
        this.templateService = templateService;
        this.templateMode = StandardTemplateModeHandlers.HTML5.getTemplateModeName();
    }


    @Override
    public void initialize()
    {
    }


    @Override
    public String getName()
    {
        return NAME;
    }


    @Override
    public Integer getOrder()
    {
        return order;
    }


    public void setOrder(final int order)
    {
        this.order = order;
    }


    @Override
    public TemplateResolution resolveTemplate(final TemplateProcessingParameters tpp)
    {
        final GetTemplateRequest getTemplateRequest = new GetTemplateRequest(
            tpp.getTemplateName(),
            new Locale(tpp.getContext().getLocale().getLanguage().toUpperCase()));

        final Result<String, GetTemplateError> templateResult = templateService.getTemplateByName(getTemplateRequest);

        if (!templateResult.isSuccess())
        {
            logger.info("Resolve template failed : getTemplateRequest={}, error={}",
                getTemplateRequest, templateResult.getErrorCode());
            return null;
        }

        final IResourceResolver resourceResolver = new FixedMemoryResourceResolver(templateResult.getResult());
        final ITemplateResolutionValidity validity = new NonCacheableTemplateResolutionValidity();
        return new TemplateResolution(tpp.getTemplateName(), tpp.getTemplateName(),
            resourceResolver, StandardCharsets.UTF_8.toString(),
            templateMode, validity);
    }


    private class FixedMemoryResourceResolver implements IResourceResolver
    {
        private static final String NAME = "FixedMemoryResourceResolver";

        private final String templateContent;


        public FixedMemoryResourceResolver(final String templateContent)
        {
            Validate.notNull(templateContent, "Template content must be non-null");
            this.templateContent = templateContent;
        }


        @Override
        public String getName()
        {
            return NAME;
        }


        @Override
        public InputStream getResourceAsStream(final TemplateProcessingParameters tpp, final String templateName)
        {
            return new ByteArrayInputStream(templateContent.getBytes());
        }
    }
}
