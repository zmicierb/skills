package com.barysevich.project.kafka;


import com.barysevich.project.api.async.exporter.MailNotificationExporter;
import com.barysevich.project.api.async.listener.MailNotificationListener;
import com.barysevich.project.kafka.api.KafkaProcessor;
import com.barysevich.project.kafka.api.MessageExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.barysevich.project.kafka.exporter.MailNotificationKafkaExporter;
import com.barysevich.project.kafka.listener.MailNotificationKafkaListener;


@Configuration
@AutoConfigureBefore(KafkaAutoConfiguration.class)
public class MailServiceKafkaAutoConfiguration
{
    @Value("${kafka-topics.mail_service}")
    private String resultTopic;


    @Bean
    @ConditionalOnMissingBean(MailNotificationExporter.class)
    public MailNotificationKafkaExporter mailNotificationExporter(final MessageExporter messageExporter)
    {
        return new MailNotificationKafkaExporter(messageExporter, resultTopic);
    }


    @Bean
    @ConditionalOnBean(MailNotificationListener.class)
    public MailNotificationKafkaListener mailNotificationListener(
            final MailNotificationListener mailNotificationListener,
            final KafkaProcessor kafkaProcessor)
    {
        return new MailNotificationKafkaListener(mailNotificationListener, kafkaProcessor);
    }
}