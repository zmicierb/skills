package com.barysevich.project.mailservice.transport;

import com.barysevich.project.mailservice.api.MailResult;
import com.barysevich.project.mailservice.message.MailMessage;

public interface MailTransport {
    MailResult sendEmail(final MailMessage message);
}
