package com.barysevich.project.core.configuration;

import com.barysevich.project.api.NotificationType;
import com.barysevich.project.api.model.MailNotificationMessage;
import com.barysevich.project.commons.queue.QueueBuilder;
import com.barysevich.project.commons.queue.QueueComponentHolder;
import com.barysevich.project.commons.queue.QueueProperties;
import com.barysevich.project.core.processor.NotificationProcessor;
import com.barysevich.project.core.processor.dao.NotificationExportLog;
import com.barysevich.project.core.queue.NotificationInboundQueue;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Конфигурация очереди.
 */
@Configuration
@EnableConfigurationProperties(QueueProperties.class)
public class QueueConfiguration
{
    @Bean
    public NotificationInboundQueue notificationInboundQueue(
            final QueueProperties queueProperties,
            final QueueComponentHolder queueComponentHolder,
            final NotificationExportLog NotificationExportLog,
            final Collection<NotificationProcessor> processors)
    {
        final QueueBuilder<MailNotificationMessage> queueBuilder = QueueBuilder.<MailNotificationMessage>builder()
                .withQueueProperties(queueProperties)
                .withQueueName("notificationInboundQueue")
                .withJsonSerializer()
                .withJsonDeserializer(MailNotificationMessage.class)
                .withQueueComponentHolder(queueComponentHolder)
                .build();

        final Map<NotificationType, NotificationProcessor> processorsMap = processors.stream()
                .collect(Collectors.toMap(NotificationProcessor::getNotificationType, Function.identity()));

        return new NotificationInboundQueue(queueBuilder, NotificationExportLog, processorsMap);
    }
}
