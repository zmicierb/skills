package com.barysevich.project.kafka;

import java.util.Objects;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.retry.support.RetryTemplate;

public class KafkaListenerContainerFactoryBuilder<K, V> {
    private final ConcurrentKafkaListenerContainerFactory<K, V> factory;


    private KafkaListenerContainerFactoryBuilder(final ConsumerFactory<K, V> consumerFactory)
    {
        this.factory = new ConcurrentKafkaListenerContainerFactory<>();

        this.factory.setConsumerFactory(consumerFactory);
    }


    static <T, R> KafkaListenerContainerFactoryBuilder<T, R> newBuilder(final ConsumerFactory<T, R> consumerFactory)
    {
        return new KafkaListenerContainerFactoryBuilder<>(consumerFactory);
    }


    KafkaListenerContainerFactoryBuilder<K, V> withRecordFilterStrategy(final RecordFilterStrategy<K, V> strategy)
    {
        factory.setAckDiscarded(true);
        factory.setRecordFilterStrategy(strategy);

        return this;
    }


    KafkaListenerContainerFactoryBuilder<K, V> withKafkaProperties(final KafkaProperties kafkaProperties)
    {
        final KafkaProperties.Listener listener = kafkaProperties.getListener();

        final ContainerProperties containerProperties = factory.getContainerProperties();

        if (Objects.nonNull(listener.getAckMode()))
        {
            containerProperties.setAckMode(listener.getAckMode());
        }

        if (Objects.nonNull(listener.getPollTimeout()))
        {
            containerProperties.setPollTimeout(listener.getPollTimeout());
        }

        if (Objects.nonNull(listener.getConcurrency()))
        {
            factory.setConcurrency(listener.getConcurrency());
        }

        return this;
    }


    KafkaListenerContainerFactoryBuilder<K, V> withRetryTemplate(final RetryTemplate retryTemplate)
    {
        factory.setRetryTemplate(retryTemplate);

        return this;
    }


    public ConcurrentKafkaListenerContainerFactory<K, V> build()
    {
        return factory;
    }
}
