package com.barysevich.project.core.transport;


import com.barysevich.project.api.MailResult;
import com.barysevich.project.core.message.MailMessage;


public interface MailTransport
{
    MailResult sendEmail(final MailMessage message);
}
