package com.barysevich.project.kafka;


import java.util.Optional;

import com.barysevich.project.utils.SerializationUtils;
import com.fasterxml.jackson.databind.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class MessageWrapperUtils
{
    private static final Logger logger = LoggerFactory.getLogger(MessageWrapperUtils.class);


    /**
     * Извлечение MessageWrapper из Json
     */
    static <T> Optional<MessageWrapper<T>> fromJson(final String value, final Class<T> clazz)
    {
        logger.trace("get wrapper: json={} class={}", value, clazz);
        
        final boolean isWrapped = value.contains(MessageWrapper.PAYLOAD_FIELD_NAME)
                && value.contains(MessageWrapper.HEADERS_FIELD_NAME);

        try
        {
            if (isWrapped)
            {
                final JavaType javaType = SerializationUtils.MAPPER.getTypeFactory()
                        .constructParametricType(MessageWrapper.class, clazz);

                final MessageWrapper<T> wrapper = SerializationUtils.fromJson(value, javaType);
                logger.trace("obtained: {}", wrapper);
                return Optional.of(wrapper);
            }
            else
            {
                final T payload = SerializationUtils.fromJson(value, clazz);
                final MessageWrapper<T> wrapper = MessageWrapper.of(payload);
                logger.trace("obtained: {}", wrapper);
                return Optional.of(wrapper);
            }

        }
        catch (final IllegalArgumentException e)
        {
            logger.trace("getting wrapper failed: {}", e.getMessage());
            return Optional.empty();
        }
    }
}