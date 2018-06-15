package com.barysevich.project.core;


import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.barysevich.project.api.MailResult;
import com.barysevich.project.api.MailService;
import com.barysevich.project.api.NotificationType;
import com.barysevich.project.api.common.SendMailError;
import com.barysevich.project.api.request.SendMailRequest;
import com.barysevich.project.core.processor.NotificationProcessor;
import com.barysevich.project.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MailServiceImpl implements MailService
{
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private final Map<NotificationType, NotificationProcessor> processorsMap;


    @Autowired
    public MailServiceImpl(
        final Set<NotificationProcessor> notificationProcessors)
    {
        this.processorsMap = notificationProcessors
            .stream()
            .collect(Collectors.toMap(NotificationProcessor::getNotificationType, Function.identity()));
    }


    @Override
    public Result<Void, SendMailError> sendMail(final SendMailRequest sendMailRequest)
    {
        final NotificationProcessor processor = processorsMap.get(sendMailRequest.getNotificationType());

        if (processor == null)
        {
            logger.info("Processor for NotificationType doesn't exist: NotificationType={}",
                sendMailRequest.getNotificationType());
            return Result.failed(SendMailError.MAIL_NOT_FOUND);
        }

        final MailResult mailResult = processor.process(sendMailRequest);

        if (!mailResult.isSuccess())
        {
            logger.info("Can't send mail: notificationId=" + sendMailRequest.getNotificationId());
            return Result.failed(SendMailError.TECHNICAL_ERROR);
        }

        return Result.success();
    }
}
