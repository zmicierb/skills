//package com.barysevich.project.core.queue;
//
//
//import java.util.Map;
//
//import com.barysevich.project.api.MailResult;
//import com.barysevich.project.api.NotificationType;
//import com.barysevich.project.api.model.MailNotificationMessage;
//import com.barysevich.project.core.model.NotificationData;
//import com.barysevich.project.core.processor.NotificationProcessor;
//import com.barysevich.project.core.processor.dao.NotificationExportLog;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
///**
// * Очередь для обработки входящих уведомлений.
// */
//public class NotificationInboundQueue extends BaseQueue<MailNotificationMessage>
//{
//    private static final Logger logger = LoggerFactory.getLogger(NotificationInboundQueue.class);
//
//    private final NotificationExportLog notificationExportLog;
//
//    private final Map<NotificationType, NotificationProcessor> processorsMap;
//
//
//    public NotificationInboundQueue(final QueueBuilder<MailNotificationMessage> queueBuilder,
//                                    final NotificationExportLog notificationExportLog,
//                                    final Map<NotificationType, NotificationProcessor> processorsMap)
//    {
//        super(queueBuilder);
//        this.notificationExportLog = notificationExportLog;
//        this.processorsMap = processorsMap;
//    }
//
//
//    @Override
//    public QueueAction process(final Task<MailNotificationMessage> task)
//    {
//        final NotificationData notificationData = map(task.getData());
//        if (notificationExportLog.isAlreadyProcessed(notificationData.getNotificationId()))
//        {
//            logger.debug("Notification is duplicate: notificationId={}", notificationData.getNotificationId());
//            return QueueAction.DEQUEUE;
//        }
//        final NotificationProcessor processor = processorsMap.get(notificationData.getNotificationType());
//        if (processor == null)
//        {
//            logger.info("Processor for NotificationType doesn't exist: NotificationType={}",
//                notificationData.getNotificationType());
//            return QueueAction.DEQUEUE;
//        }
//        final MailResult mailResult = processor.process(notificationData);
//        if (!mailResult.isSuccess())
//        {
//            logger.info("Can't send mail: notificationId=" + notificationData.getNotificationId());
//            return QueueAction.REENQUEUE_ON_ERROR;
//        }
//
//        notificationExportLog.log(notificationData.getNotificationId());
//        return QueueAction.DEQUEUE;
//    }
//
//
//    /**
//     * Преобразует сообщения из кафки в объекты, которые обрабатывает очередь.
//     */
//    private NotificationData map(final MailNotificationMessage source)
//    {
//        return NotificationData.builder()
//            .withNotificationId(source.getNotificationId())
//            .withEmail(source.getEmail())
//            .withMessageData(source.getMessageData())
//            .withNotificationType(source.getNotificationType())
//            .withLocale(source.getLocale())
//            .build();
//    }
//}
//
