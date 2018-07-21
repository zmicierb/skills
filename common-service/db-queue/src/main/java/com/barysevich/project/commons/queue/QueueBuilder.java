package com.barysevich.project.commons.queue;

import com.barysevich.project.utils.SerializationUtils;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * Билдер для {@link BaseQueue}
 */
public class QueueBuilder<DataT>
{
    private final QueueProperties queueProperties;

    private final String queueName;

    private final Function<DataT, String> serializer;

    private final Function<String, DataT> deserializer;

    private final QueueComponentHolder queueComponentHolder;


    private QueueBuilder(final Builder<DataT> builder)
    {
        this.queueProperties = requireNonNull(builder.queueProperties);
        this.serializer = requireNonNull(builder.serializer);
        this.deserializer = requireNonNull(builder.deserializer);
        this.queueName = requireNonNull(builder.queueName);
        this.queueComponentHolder = requireNonNull(builder.queueComponentHolder);
    }


    public static <DataT> Builder<DataT> builder()
    {
        return new Builder<>();
    }


    public QueueProperties getQueueProperties()
    {
        return queueProperties;
    }


    public String getQueueName()
    {
        return queueName;
    }


    public Function<DataT, String> getSerializer()
    {
        return serializer;
    }


    public Function<String, DataT> getDeserializer()
    {
        return deserializer;
    }


    public QueueComponentHolder getQueueComponentHolder()
    {
        return queueComponentHolder;
    }


    public static final class Builder<DataT>
    {
        private QueueProperties queueProperties;

        private String queueName;

        private Function<DataT, String> serializer;

        private Function<String, DataT> deserializer;

        private QueueComponentHolder queueComponentHolder;


        private Builder()
        {
        }


        public Builder<DataT> withQueueProperties(final QueueProperties val)
        {
            this.queueProperties = val;
            return this;
        }


        public Builder<DataT> withQueueName(final String val)
        {
            this.queueName = val;
            return this;
        }


        public Builder<DataT> withSerializer(final Function<DataT, String> val)
        {
            this.serializer = val;
            return this;
        }


        public Builder<DataT> withDeserializer(final Function<String, DataT> val)
        {
            this.deserializer = val;
            return this;
        }


        public Builder<DataT> withQueueComponentHolder(final QueueComponentHolder val)
        {
            this.queueComponentHolder = val;
            return this;
        }


        public Builder<DataT> withJsonSerializer()
        {
            this.serializer = SerializationUtils::toJson;
            return this;
        }


        public Builder<DataT> withJsonDeserializer(final Class<DataT> clazz)
        {
            this.deserializer = data -> SerializationUtils.fromJson(data, clazz);
            return this;
        }


        public QueueBuilder<DataT> build()
        {
            return new QueueBuilder<>(this);
        }
    }
}
