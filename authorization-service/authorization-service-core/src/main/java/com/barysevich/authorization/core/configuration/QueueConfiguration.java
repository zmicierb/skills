package com.barysevich.authorization.core.configuration;


import com.barysevich.authorization.core.dao.RegistrationDAO;
import com.barysevich.authorization.core.queue.RegistrationQueue;
import com.barysevich.authorization.core.queue.RegistrationQueueData;
import com.barysevich.project.commons.queue.QueueBuilder;
import com.barysevich.project.commons.queue.QueueComponentHolder;
import com.barysevich.project.commons.queue.QueueProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Конфигурация очереди.
 */
@Configuration
@EnableConfigurationProperties(QueueProperties.class)
public class QueueConfiguration
{
    @Bean
    public RegistrationQueue registrationQueue(
        final QueueProperties queueProperties,
        final QueueComponentHolder queueComponentHolder,
        final RegistrationDAO registrationDAO)
    {
        final QueueBuilder<RegistrationQueueData> queueBuilder = QueueBuilder.<RegistrationQueueData>builder()
                .withQueueProperties(queueProperties)
                .withQueueName("registrationInboundQueue")
                .withJsonSerializer()
                .withJsonDeserializer(RegistrationQueueData.class)
                .withQueueComponentHolder(queueComponentHolder)
                .build();

        return new RegistrationQueue(queueBuilder, registrationDAO);
    }
}
