package com.barysevich.project.kafka.api;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Процессор, который используется для предобработки сообщений из кафки и передачи на обработку консьюмеру
 */
public interface KafkaProcessor
{
    <T> void process(
            final ConsumerRecord<String, String> record,
            final Class<T> clazz,
            final Consumer<T> consumer,
            final Acknowledgment ack);


    <T> void process(
            final ConsumerRecord<String, String> record,
            final Class<T> clazz,
            final BiConsumer<String, T> processConsumer,
            final Acknowledgment ack);


    <T> void processRecord(
            final ConsumerRecord<String, String> record,
            final Class<T> clazz,
            final BiConsumer<T, ConsumerRecord> consumer,
            final Acknowledgment ack);
}
