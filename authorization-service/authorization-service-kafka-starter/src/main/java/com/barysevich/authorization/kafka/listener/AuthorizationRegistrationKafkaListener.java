package com.barysevich.authorization.kafka.listener;


import com.barysevich.authorization.api.async.AuthorizationRegistrationListener;
import com.barysevich.authorization.api.async.RegistrationInfoMessage;
import com.barysevich.project.kafka.api.KafkaProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;


/**
 * Кафка-слушатель
 */
public class AuthorizationRegistrationKafkaListener
{
    private final AuthorizationRegistrationListener authorizationRegistrationListener;

    private final KafkaProcessor kafkaProcessor;


    public AuthorizationRegistrationKafkaListener(
            final AuthorizationRegistrationListener authorizationRegistrationListener,
            final KafkaProcessor kafkaProcessor)
    {
        this.authorizationRegistrationListener = authorizationRegistrationListener;
        this.kafkaProcessor = kafkaProcessor;
    }


    @KafkaListener(topics = "${kafka-topics.authorization_service}")
    public void onResult(final ConsumerRecord<String, String> record, final Acknowledgment ack)
    {
        kafkaProcessor.process(record, RegistrationInfoMessage.class, authorizationRegistrationListener::onReceive, ack);
    }
}