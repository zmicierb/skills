package com.barysevich.project.kafka;

//import com.barysevich.project.kafka.api.KafkaProcessor;
//import com.barysevich.project.kafka.api.MessageExporter;
import com.barysevich.project.kafka.api.KafkaProcessor;
import com.barysevich.project.kafka.api.MessageExporter;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@AutoConfigureBefore(KafkaAutoConfiguration.class)
@EnableConfigurationProperties(KafkaProperties.class)
public class CommonKafkaAutoConfiguration {
    private final KafkaProperties kafkaProperties;


    @Autowired
    public CommonKafkaAutoConfiguration(final KafkaProperties kafkaProperties)
    {
        this.kafkaProperties = kafkaProperties;
    }


    @Bean
    @ConditionalOnMissingBean(ProducerFactory.class)
    public ProducerFactory<String, String> producerFactory()
    {
        final StringSerializer serializer = new StringSerializer();

        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties(), serializer, serializer);
    }


    @Bean
    @ConditionalOnMissingBean(KafkaTemplate.class)
    public KafkaTemplate<String, String> kafkaTemplate(final ProducerFactory<String, String> producerFactory)
    {
        return new KafkaTemplate<>(producerFactory);
    }


    @Bean
    @ConditionalOnMissingBean(ConsumerFactory.class)
    public ConsumerFactory<String, String> consumerFactory()
    {
        final StringDeserializer deserializer = new StringDeserializer();

        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), deserializer, deserializer);
    }


    @Bean
    @ConditionalOnMissingBean(RecordFilterStrategy.class)
    public RecordFilterStrategy<String, String> filterStrategy()
    {
        return record -> false;
    }


    @Bean
    @ConditionalOnMissingBean(RetryTemplate.class)
    public RetryTemplate retryTemplate()
    {
        final RetryTemplate retryTemplate = new RetryTemplate();

        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(Integer.MAX_VALUE));
        retryTemplate.setBackOffPolicy(new FixedBackOffPolicy());
        retryTemplate.setThrowLastExceptionOnExhausted(true);

        return retryTemplate;
    }


    @Bean
    @ConditionalOnMissingBean(ConcurrentKafkaListenerContainerFactory.class)
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            final ConsumerFactory<String, String> consumerFactory, final RetryTemplate retryTemplate,
            final RecordFilterStrategy<String, String> filterStrategy)
    {
        return KafkaListenerContainerFactoryBuilder.newBuilder(consumerFactory)
                .withRecordFilterStrategy(filterStrategy)
                .withKafkaProperties(kafkaProperties)
                .withRetryTemplate(retryTemplate)
                .build();
    }


    @Bean
    @ConditionalOnMissingBean(MessageExporter.class)
    public MessageExporter kafkaMessageExporter(
            final KafkaTemplate<String, String> kafkaTemplate)
    {
        return new KafkaMessageExporter(kafkaTemplate);
    }


    @Bean
    @ConditionalOnMissingBean(KafkaProcessor.class)
    public KafkaProcessor defaultKafkaProcessor()
    {
        return new DefaultKafkaProcessor();
    }
}
