package com.barysevich.project.api.async.exporter;


import com.barysevich.project.api.model.MailNotificationMessage;


/**
 * Экспортер сообщений в mail-service
 */
public interface MailNotificationExporter
{
    boolean exportMessage(final MailNotificationMessage message);
}