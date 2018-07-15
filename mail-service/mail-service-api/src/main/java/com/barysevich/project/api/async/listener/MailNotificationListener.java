package com.barysevich.project.api.async.listener;


import com.barysevich.project.api.model.MailNotificationMessage;


/**
 * Слушатель сообщений, которые экспортятся в mail-service
 */
public interface MailNotificationListener
{
    void onReceive(final MailNotificationMessage message);
}