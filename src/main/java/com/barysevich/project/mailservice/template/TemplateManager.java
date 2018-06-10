package com.barysevich.project.mailservice.template;

import com.barysevich.project.mailservice.message.MailMessage;
import com.barysevich.project.mailservice.template.mail.MailTemplate;

public interface TemplateManager
{
    Either<Exception, MailMessage> process(final MailTemplate template);
}
