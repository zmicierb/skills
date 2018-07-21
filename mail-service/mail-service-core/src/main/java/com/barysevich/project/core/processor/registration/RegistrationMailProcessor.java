package com.barysevich.project.core.processor.registration;


import java.util.Optional;

import com.barysevich.project.api.NotificationType;
import com.barysevich.project.core.model.NotificationData;
import com.barysevich.project.core.processor.NotificationProcessor;
import com.barysevich.project.core.template.TemplateManager;
import com.barysevich.project.core.template.mail.MailTemplate;
import com.barysevich.project.core.template.mail.RegistrationTemplate;
import com.barysevich.project.core.transport.MailTransport;
import org.springframework.stereotype.Component;


@Component
public class RegistrationMailProcessor extends NotificationProcessor
{
    protected RegistrationMailProcessor(final MailTransport transport,
                                        final TemplateManager templateManager)
    {
        super(transport, templateManager);
    }


    @Override
    public NotificationType getNotificationType()
    {
        return NotificationType.USER_REGISTERED;
    }


    @Override
    protected Optional<MailTemplate> getTemplate(final NotificationData data)
    {
//        final RegistrationInvitationMailPayload templateData = SerializationUtils
//            .fromJson(request.getMessageData(), RegistrationInvitationMailPayload.class);

        final MailTemplate mailTemplate = RegistrationTemplate.builder()
            .withFromAddress("test@test.com")
            .withToAddress(data.getEmail().asString())
            .withLocale(data.getLocale())
            .build()
            .done();

        return Optional.of(mailTemplate);
    }
}
