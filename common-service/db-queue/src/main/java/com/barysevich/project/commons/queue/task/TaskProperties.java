package com.barysevich.project.commons.queue.task;

import java.time.Duration;

import static java.util.Objects.requireNonNull;

/**
 * Параметры задачи на исполнение в очереди
 */
public class TaskProperties
{
    private final Duration delay;

    private final String queueName;

//    private final Span span;


    private TaskProperties(final Duration delay, final String queueName)
    {
        this.delay = delay;
        this.queueName = queueName;
//        this.span = span;
    }


    public static TaskProperties create(final Duration delay, final String queueName)
    {
        requireNonNull(delay);
        requireNonNull(queueName);
//        requireNonNull(span);
        return new TaskProperties(delay, queueName);
    }


//    public static TaskProperties createWithoutTracing(final Duration delay, final String queueName)
//    {
//        requireNonNull(delay);
//        requireNonNull(queueName);
//        return new TaskProperties(delay, queueName, null);
//    }


    public Duration getDelay()
    {
        return delay;
    }


    public String getQueueName()
    {
        return queueName;
    }


//    public Optional<Span> getSpan()
//    {
//        return Optional.ofNullable(span);
//    }


    @Override
    public String toString() {
        return "TaskProperties{" +
                "delay=" + delay +
                ", queueName='" + queueName + '\'' +
                '}';
    }
}
