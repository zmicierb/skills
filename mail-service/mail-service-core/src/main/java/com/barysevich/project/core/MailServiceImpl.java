//package com.barysevich.project.core;
//
//
//import java.util.Map;
//import java.util.Set;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//import com.barysevich.project.api.MailResult;
//import com.barysevich.project.api.MailService;
//import com.barysevich.project.api.NotificationType;
//import com.barysevich.project.api.common.SendMailError;
//import com.barysevich.project.api.model.MailNotificationMessage;
//import com.barysevich.project.core.model.NotificationData;
//import com.barysevich.project.core.processor.NotificationProcessor;
//import com.barysevich.project.result.Result;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class MailServiceImpl implements MailService
//{
//    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
//
//    private final Map<NotificationType, NotificationProcessor> processorsMap;
//
//
//    @Autowired
//    public MailServiceImpl(
//        final Set<NotificationProcessor> notificationProcessors)
//    {
//        this.processorsMap = notificationProcessors
//            .stream()
//            .collect(Collectors.toMap(NotificationProcessor::getNotificationType, Function.identity()));
//    }
//
//
//    @Override
//    public Result<Void, SendMailError> sendMail(final MailNotificationMessage mailNotificationMessage)
//    {
//        final NotificationProcessor processor = processorsMap.get(mailNotificationMessage.getNotificationType());
//
//        if (processor == null)
//        {
//            logger.info("Processor for NotificationType doesn't exist: NotificationType={}",
//                    mailNotificationMessage.getNotificationType());
//            return Result.failed(SendMailError.MAIL_NOT_FOUND);
//        }
//
//        final MailResult mailResult = processor.process(map(mailNotificationMessage));
//
//        if (!mailResult.isSuccess())
//        {
//            logger.info("Can't send mail: notificationId=" + mailNotificationMessage.getNotificationId());
//            return Result.failed(SendMailError.TECHNICAL_ERROR);
//        }
//
//        return Result.success();
//    }
//
//
//    /**
//     * Преобразует сообщения из запроса в объекты, которые обрабатывает процессор.
//     */
//    private NotificationData map(final MailNotificationMessage source)
//    {
//        return NotificationData.builder()
//                .withNotificationId(source.getNotificationId())
//                .withEmail(source.getEmail())
//                .withMessageData(source.getMessageData())
//                .withNotificationType(source.getNotificationType())
//                .withLocale(source.getLocale())
//                .build();
//    }
//}
