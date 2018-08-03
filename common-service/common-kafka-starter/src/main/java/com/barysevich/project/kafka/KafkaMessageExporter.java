package com.barysevich.project.kafka;


import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.barysevich.project.kafka.api.MessageExporter;
//import com.google.common.collect.ImmutableMap;
import com.barysevich.project.utils.SerializationUtils;
import com.barysevich.project.utils.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.Tracer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static java.util.Objects.requireNonNull;


/**
 * Осуществляет экспорт сообщений в кафку.
 * <p>
 * Оборачивает экспортируемый объект в {@link MessageWrapper}
 * <p>
 * При сборке враппера добавляет в хэдеры данные о трассировке.
 * <p>
 * WARN: Использовать данный экспортер следует при полной уверенности, что листенеры умеют распаковывать враппер.
 */
public class KafkaMessageExporter implements MessageExporter
{
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageExporter.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

//    private final Tracer tracer;


    KafkaMessageExporter(final KafkaTemplate<String, String> kafkaTemplate
//                                  final Tracer tracer)
    )
    {
        this.kafkaTemplate = kafkaTemplate;
//        this.tracer = tracer;
    }


    @Override
    public <T> boolean export(final String topic, final T value)
    {
        return exportInternal(topic, null, value);
    }


    @Override
    public <T> boolean export(final String topic, final String key, final T value)
    {
        return exportInternal(topic, requireNonNull(key), value);
    }


    private <T> boolean exportInternal(final String topic, final String key, final T value)
    {
        logger.trace("Exporting message {}", value);

        final String message = SerializationUtils.toJson(value);

        final CompletableFuture<Boolean> future = new CompletableFuture<>();

        final ListenableFuture<SendResult<String, String>> listenableFuture = Objects.isNull(key)
            ? kafkaTemplate.send(topic, message) : kafkaTemplate.send(topic, key, message);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>()
        {
            @Override
            public void onFailure(final Throwable ex)
            {
                logger.warn("Unable to export message", ex);

                future.completeExceptionally(ex);
            }


            @Override
            public void onSuccess(final SendResult<String, String> result)
            {
                logger.trace("Message was successfully exported");

                future.complete(true);
            }
        });

        return Try.apply(future::get).orElse(false);
    }


//    private <T> String prepareMessage(final T value)
//    {
//        if (tracer.isTracing())
//        {
//            final ImmutableMap<String, String> headers = ImmutableMap.of(
//                    Span.TRACE_ID_NAME, String.valueOf(tracer.getCurrentSpan().getTraceId()),
//                    Span.SPAN_ID_NAME, String.valueOf(tracer.getCurrentSpan().getSpanId()),
//                    Span.SPAN_EXPORT_NAME, String.valueOf(tracer.getCurrentSpan().isExportable()));
//
//            final MessageWrapper<T> wrapper = MessageWrapper.of(value, headers);
//            return SerializationUtils.toJson(wrapper);
//        }
//        else
//        {
//            return SerializationUtils.toJson(value);
//        }
//    }
}