//package com.barysevich.project.kafka;
//
//import java.util.Map;
//import java.util.Optional;
//import java.util.Set;
//import java.util.function.BiConsumer;
//import java.util.function.Consumer;
//
//import com.google.common.collect.ImmutableSet;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.Tracer;
//import org.springframework.kafka.support.Acknowledgment;
//
//import com.barysevich.project.kafka.api.KafkaProcessor;
//
///**
// * Обработчик принимаемых сообщений.
// *
// * Трассирует принимаемые сообщения из кафки
// */
//public class TraceableKafkaProcessor implements KafkaProcessor
//{
//    private static final Logger logger = LoggerFactory.getLogger(TraceableKafkaProcessor.class);
//
//    private static final Set<String> TRACE_HEADERS = ImmutableSet.of(TRACE_ID_NAME, SPAN_ID_NAME, SPAN_EXPORT_NAME);
//
//    private final Tracer tracer;
//
//
//    public TraceableKafkaProcessor(final Tracer tracer)
//    {
//        this.tracer = tracer;
//    }
//
//
//    @Override
//    public <T> void process(
//            final ConsumerRecord<String, String> record,
//            final Class<T> clazz,
//            final Consumer<T> consumer,
//            final Acknowledgment ack)
//    {
//        logger.trace("obtained new message: {}", record.value());
//        final Optional<MessageWrapper<T>> wrapperOptional = MessageWrapperUtils.fromJson(record.value(), clazz);
//
//        if (!wrapperOptional.isPresent())
//        {
//            // ack не делаем
//            logger.warn("deserialization failed for: {}", record.value());
//            return;
//        }
//
//        final MessageWrapper<T> wrapper = wrapperOptional.get();
//
//        consumeWithTrace(record.topic(), consumer, ack, wrapper);
//    }
//
//
//    @Override
//    public <T> void process(final ConsumerRecord<String, String> record, final Class<T> clazz,
//                            final BiConsumer<String, T> processConsumer, final Acknowledgment ack)
//    {
//        process(record, clazz, deserializedMessage -> processConsumer.accept(record.key(), deserializedMessage), ack);
//    }
//
//
//    @Override
//    public <T> void processRecord(final ConsumerRecord<String, String> record, final Class<T> clazz,
//                                  final BiConsumer<T, ConsumerRecord> consumer, final Acknowledgment ack)
//    {
//        process(record, clazz, deserializedMessage -> consumer.accept(deserializedMessage, record), ack);
//    }
//
//
//    private <T> void consumeWithTrace(
//            final String topic,
//            final Consumer<T> consumer,
//            final Acknowledgment ack,
//            final MessageWrapper<T> wrapper)
//    {
//        final Optional<Span> parent = extractParentSpan(wrapper.getHeaders());
//
//        final Span newSpan = parent.map(span -> tracer.createSpan("kafka:/" + topic, span))
//                .orElseGet(() -> tracer.createSpan("kafka:/" + topic));
//
//        try
//        {
//            consumer.accept(wrapper.getPayload());
//
//            ack.acknowledge();
//        }
//        catch (final Throwable throwable)
//        {
//            logger.warn("Couldn't process message: {}", wrapper.getPayload());
//            logger.warn(throwable.getMessage(), throwable);
//        }
//        finally
//        {
//            tracer.close(newSpan);
//        }
//    }
//
//
//    private Optional<Span> extractParentSpan(final Map<String, String> headers)
//    {
//        if (headers.keySet().containsAll(TRACE_HEADERS))
//        {
//            final Span parent = Span.builder()
//                    .traceId(Long.valueOf(headers.get(TRACE_ID_NAME)))
//                    .spanId(Long.valueOf(headers.get(SPAN_ID_NAME)))
//                    .exportable(Boolean.valueOf(headers.get(SPAN_EXPORT_NAME)))
//                    .build();
//
//            return Optional.of(parent);
//        }
//        return Optional.empty();
//    }
//}
