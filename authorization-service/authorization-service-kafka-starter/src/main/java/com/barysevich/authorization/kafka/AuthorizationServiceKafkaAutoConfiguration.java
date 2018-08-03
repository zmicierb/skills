package com.barysevich.authorization.kafka;


import com.barysevich.authorization.api.async.AuthorizationRegistrationExporter;
import com.barysevich.authorization.api.async.AuthorizationRegistrationListener;
import com.barysevich.authorization.kafka.exporter.AuthorizationRegistrationKafkaExporter;
import com.barysevich.authorization.kafka.listener.AuthorizationRegistrationKafkaListener;
import com.barysevich.project.kafka.api.KafkaProcessor;
import com.barysevich.project.kafka.api.MessageExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureBefore(KafkaAutoConfiguration.class)
public class AuthorizationServiceKafkaAutoConfiguration
{
    @Value("${kafka-topics.authorization_service}")
    private String resultTopic;


    @Bean
    @ConditionalOnMissingBean(AuthorizationRegistrationExporter.class)
    public AuthorizationRegistrationKafkaExporter authorizationRegistrationExporter(
        final MessageExporter messageExporter)
    {
        return new AuthorizationRegistrationKafkaExporter(messageExporter, resultTopic);
    }


    @Bean
    @ConditionalOnBean(AuthorizationRegistrationListener.class)
    public AuthorizationRegistrationKafkaListener authorizationRegistrationListener(
        final AuthorizationRegistrationListener authorizationRegistrationListener,
        final KafkaProcessor kafkaProcessor)
    {
        return new AuthorizationRegistrationKafkaListener(authorizationRegistrationListener, kafkaProcessor);
    }
}
