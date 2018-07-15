package com.barysevich.project.kafka.listener;


import com.barysevich.project.api.async.listener.MailNotificationListener;
import com.barysevich.project.api.model.MailNotificationMessage;
import com.barysevich.project.kafka.api.KafkaProcessor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;


/**
 * Кафка-слушатель
 */
public class MailNotificationKafkaListener
{
    private final MailNotificationListener notificationResultListener;

    private final KafkaProcessor kafkaProcessor;


    public MailNotificationKafkaListener(
            final MailNotificationListener mailNotificationListener,
            final KafkaProcessor kafkaProcessor)
    {
        this.notificationResultListener = mailNotificationListener;
        this.kafkaProcessor = kafkaProcessor;
    }


    @KafkaListener(topics = "${kafka-topics.mail_service}")
    public void onResult(final ConsumerRecord<String, String> record, final Acknowledgment ack)
    {
        kafkaProcessor.process(record, MailNotificationMessage.class, notificationResultListener::onReceive, ack);
    }
}