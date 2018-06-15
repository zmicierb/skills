package com.barysevich.project.core.template;


import java.util.Locale;

import com.barysevich.project.core.message.MailMessage;
import com.barysevich.project.core.template.mail.MailTemplate;
import com.barysevich.project.utils.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Component
public class ThymeleafTemplateManager implements TemplateManager
{
    private static final Logger logger = LoggerFactory.getLogger(ThymeleafTemplateManager.class);

    private final TemplateEngine templateEngine;


    @Autowired
    public ThymeleafTemplateManager(final TemplateEngine templateEngine)
    {
        this.templateEngine = templateEngine;
    }


    @Override
    public Either<Exception, MailMessage> process(final MailTemplate template)
    {
        logger.trace("Processing template [{}]", template.getTemplateName());

        final Locale locale = new Locale(template.getLocale().asString());

        final Context context = new Context(locale, template.getVariables());

        try
        {
            final String html = templateEngine.process(template.getTemplateName(), context);

            logger.trace("Template [{}] was processed successfully", template.getTemplateName());

            final MailMessage message = new MailMessage(template.getHeader(), html);

            return Either.right(message);
        } catch (final Exception e)
        {
            logger.warn("Could not process template", e);

            return Either.left(e);
        }
    }
}
