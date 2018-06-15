package com.barysevich.project.core.template;


import com.barysevich.project.core.message.MailMessage;
import com.barysevich.project.core.template.mail.MailTemplate;
import com.barysevich.project.utils.Either;


public interface TemplateManager
{
    Either<Exception, MailMessage> process(final MailTemplate template);
}
