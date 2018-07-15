package com.barysevich.project.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.listener.AbstractMessageListenerContainer.AckMode;

@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {
    @Value("${kafka.bootstrap-servers}")
    private List<String> bootstrapServers;

    private String clientId;

    private Consumer consumer;

    private Producer producer;

    private Listener listener;


    public List<String> getBootstrapServers()
    {
        return this.bootstrapServers;
    }


    public String getClientId()
    {
        return this.clientId;
    }


    public Consumer getConsumer()
    {
        return this.consumer;
    }


    public Producer getProducer()
    {
        return this.producer;
    }


    public Listener getListener()
    {
        return this.listener;
    }


    public void setConsumer(final Consumer consumer)
    {
        this.consumer = consumer;
    }


    public void setProducer(final Producer producer)
    {
        this.producer = producer;
    }


    public void setListener(final Listener listener)
    {
        this.listener = listener;
    }


    public void setBootstrapServers(final List<String> bootstrapServers)
    {
        this.bootstrapServers = bootstrapServers;
    }


    public void setClientId(final String clientId)
    {
        this.clientId = clientId;
    }


    private Map<String, Object> buildCommonProperties()
    {
        Map<String, Object> properties = new HashMap<>();

        if (this.bootstrapServers != null)
        {
            properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        }

        if (this.clientId != null)
        {
            properties.put(CommonClientConfigs.CLIENT_ID_CONFIG, this.clientId);
        }

        return properties;
    }


    Map<String, Object> buildConsumerProperties()
    {
        final Map<String, Object> properties = buildCommonProperties();

        properties.putAll(this.consumer.buildProperties());

        return properties;
    }


    Map<String, Object> buildProducerProperties()
    {
        final Map<String, Object> properties = buildCommonProperties();

        properties.putAll(this.producer.buildProperties());

        return properties;
    }


    public static class Consumer
    {
        private final boolean enableAutoCommit = false;

        private String clientId;

        private String serviceName;


        public boolean getEnableAutoCommit()
        {
            return this.enableAutoCommit;
        }


        public String getClientId()
        {
            return this.clientId;
        }


        public String getServiceName()
        {
            return serviceName;
        }


        public void setClientId(final String clientId)
        {
            this.clientId = clientId;
        }


        public void setServiceName(final String serviceName)
        {
            this.serviceName = serviceName;
        }


        Map<String, Object> buildProperties()
        {
            final Map<String, Object> properties = new HashMap<>();

            properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, this.enableAutoCommit);

            if (this.serviceName != null)
            {
                properties.put(ConsumerConfig.GROUP_ID_CONFIG, this.serviceName);
            }

            if (this.clientId != null)
            {
                properties.put(ConsumerConfig.CLIENT_ID_CONFIG, this.clientId);
            }

            return properties;
        }

    }

    public static class Producer
    {
        private String acks;

        private Integer batchSize;

        private Long bufferMemory;

        private String clientId;

        private String compressionType;

        private Integer retries;


        public String getAcks()
        {
            return this.acks;
        }


        public Integer getBatchSize()
        {
            return this.batchSize;
        }


        public Long getBufferMemory()
        {
            return this.bufferMemory;
        }


        public String getClientId()
        {
            return this.clientId;
        }


        public String getCompressionType()
        {
            return this.compressionType;
        }


        public Integer getRetries()
        {
            return this.retries;
        }


        public void setAcks(final String acks)
        {
            this.acks = acks;
        }


        public void setBatchSize(final Integer batchSize)
        {
            this.batchSize = batchSize;
        }


        public void setBufferMemory(final Long bufferMemory)
        {
            this.bufferMemory = bufferMemory;
        }


        public void setClientId(final String clientId)
        {
            this.clientId = clientId;
        }


        public void setCompressionType(final String compressionType)
        {
            this.compressionType = compressionType;
        }


        public void setRetries(final Integer retries)
        {
            this.retries = retries;
        }


        Map<String, Object> buildProperties()
        {
            final Map<String, Object> properties = new HashMap<>();

            if (this.acks != null)
            {
                properties.put(ProducerConfig.ACKS_CONFIG, this.acks);
            }

            if (this.batchSize != null)
            {
                properties.put(ProducerConfig.BATCH_SIZE_CONFIG, this.batchSize);
            }

            if (this.bufferMemory != null)
            {
                properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, this.bufferMemory);
            }

            if (this.clientId != null)
            {
                properties.put(ProducerConfig.CLIENT_ID_CONFIG, this.clientId);
            }

            if (this.compressionType != null)
            {
                properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, this.compressionType);
            }

            if (this.retries != null)
            {
                properties.put(ProducerConfig.RETRIES_CONFIG, this.retries);
            }

            return properties;
        }
    }

    public static class Listener
    {
        private final AckMode ackMode = AckMode.MANUAL_IMMEDIATE;

        private Integer concurrency;

        private Long pollTimeout;


        public AckMode getAckMode()
        {
            return this.ackMode;
        }


        public Integer getConcurrency()
        {
            return this.concurrency;
        }


        public Long getPollTimeout()
        {
            return this.pollTimeout;
        }


        public void setConcurrency(final Integer concurrency)
        {
            this.concurrency = concurrency;
        }


        public void setPollTimeout(final Long pollTimeout)
        {
            this.pollTimeout = pollTimeout;
        }
    }
}
