package com.barysevich.project.core.async;


import com.barysevich.project.api.async.listener.MailNotificationListener;
import com.barysevich.project.api.model.MailNotificationMessage;
import com.barysevich.project.core.queue.NotificationInboundQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Слушатель сообщений из кафки, которые имеют тип {@link MailNotificationMessage}.
 */
@Component
public class MailNotificationListenerImpl implements MailNotificationListener
{
    private static final Logger logger = LoggerFactory.getLogger(MailNotificationListenerImpl.class);

    private final NotificationInboundQueue notificationInboundQueue;


    public MailNotificationListenerImpl(final NotificationInboundQueue notificationInboundQueue)
    {
        this.notificationInboundQueue = notificationInboundQueue;
    }


    /**
     * Принимает сообщения и кладет их в очередь на обработку.
     */
    public void onReceive(final MailNotificationMessage message)
    {
        logger.debug("NotificationMessage is received: message={}", message);
        notificationInboundQueue.enqueue(message, message.getNotificationId());
    }
}
