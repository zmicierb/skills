package com.barysevich.project.core.processor;


import java.util.Optional;

import com.barysevich.project.api.MailResult;
import com.barysevich.project.api.NotificationType;
import com.barysevich.project.core.model.NotificationData;
import com.barysevich.project.core.template.TemplateManager;
import com.barysevich.project.core.template.mail.MailTemplate;
import com.barysevich.project.core.transport.MailTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class NotificationProcessor
{
    private static final Logger logger = LoggerFactory.getLogger(NotificationProcessor.class);

    private final MailTransport transport;

    private final TemplateManager templateManager;


    protected NotificationProcessor(final MailTransport transport,
                                    final TemplateManager templateManager)
    {
        this.transport = transport;
        this.templateManager = templateManager;
    }


    /**
     * Формирует шаблон письма и отправляет его пользователю.
     */
    public MailResult process(final NotificationData data)
    {
        final Optional<MailTemplate> mailTemplateOptional = getTemplate(data);
        if (!mailTemplateOptional.isPresent())
        {
            logger.warn("Can't create mailTemplate: notificationId={}", data.getNotificationId());
            return MailResult.failed();
        }
        final MailTemplate mailTemplate = mailTemplateOptional.get();

        return sendMail(mailTemplate);
    }


    /**
     * Возвращает тип уведомления, который обработывает процессор.
     */
    abstract public NotificationType getNotificationType();


    /**
     * Возвращает объект, в котором содержится всё необходимая информация для отправки письма.
     */
    abstract protected Optional<MailTemplate> getTemplate(final NotificationData data);


    /**
     * Отправляет письмо пользователю и возвращает статус отправки.
     */
    private MailResult sendMail(final MailTemplate mailTemplate)
    {
        return templateManager.process(mailTemplate).map(transport::sendEmail).getOr(MailResult::failed);
    }
}
