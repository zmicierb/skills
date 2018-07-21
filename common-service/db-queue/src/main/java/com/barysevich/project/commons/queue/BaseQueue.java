package com.barysevich.project.commons.queue;

import com.barysevich.project.commons.queue.task.RawTask;
import com.barysevich.project.commons.queue.task.Task;
import com.barysevich.project.commons.queue.task.TaskManager;
import com.barysevich.project.commons.queue.task.TaskProperties;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Абстрактный класс очереди.
 */
public abstract class BaseQueue<DataT>
{
    private final static Logger logger = LoggerFactory.getLogger(BaseQueue.class);

    private static final Duration REENQUEUE_DURATION = Duration.ofMillis(5);

    private final QueueProperties queueProperties;

    private final TaskManager taskManager;

    private final String queueName;

    private final Function<DataT, String> serializer;

    private final Function<String, DataT> deserializer;

    private final TransactionTemplate transactionTemplate;

//    private final Tracer tracer;


    public BaseQueue(final QueueBuilder<DataT> queueBuilder)
    {
        this.queueProperties = requireNonNull(queueBuilder.getQueueProperties());
        this.taskManager = requireNonNull(queueBuilder.getQueueComponentHolder().getTaskManager());
        this.queueName = requireNonNull(queueBuilder.getQueueName());
        this.serializer = requireNonNull(queueBuilder.getSerializer());
        this.deserializer = requireNonNull(queueBuilder.getDeserializer());

        this.transactionTemplate = requireNonNull(queueBuilder.getQueueComponentHolder().getTransactionTemplate());
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

//        this.tracer = requireNonNull(queueBuilder.getQueueComponentHolder().getTracer());
    }


    abstract public QueueAction process(final Task<DataT> task);


    public void enqueue(final DataT data, final Duration delay)
    {
        addTask(data, UUID.randomUUID(), delay);
    }


    public void enqueue(final DataT data)
    {
        addTask(data, UUID.randomUUID(), Duration.ZERO);
    }


    public void enqueue(final DataT data, final UUID uuid, final Duration delay)
    {
        addTask(data, uuid, delay);
    }


    public void enqueue(final DataT data, final UUID uuid)
    {
        addTask(data, uuid, Duration.ZERO);
    }


    public String getQueueName()
    {
        return queueName;
    }


//    protected Tracer getTracer()
//    {
//        return tracer;
//    }


    private void addTask(final DataT data, final UUID uuid, final Duration delay)
    {
        logger.info("add new task: data={}, uuid={}, delay={}", data, uuid, delay);
        requireNonNull(data);

        final TaskProperties properties = TaskProperties.create(delay, queueName);

        taskManager.addTask(serializer.apply(data), uuid, properties);
    }


    public void start()
    {
        final ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat(queueName + "-%d")
                .setDaemon(false)
                .build();

        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(
                queueProperties.getThreadPoolSize(), threadFactory);

        for (int i = 0; i < queueProperties.getThreadPoolSize(); i++)
        {
            executorService.scheduleWithFixedDelay(
                    this::getNextTaskAndProcess,
                    0,
                    queueProperties.getBetweenTaskTimeoutMs(),
                    TimeUnit.MILLISECONDS);
        }

        logger.debug("queue started with: name={}, properties={}", queueName, queueProperties);
    }


    private void getNextTaskAndProcess()
    {
        transactionTemplate.execute(new TransactionCallbackWithoutResult()
        {
            @Override
            protected void doInTransactionWithoutResult(final TransactionStatus status)
            {
                try
                {
                    Optional<RawTask> nextTaskOpt = log(() ->
                            taskManager.getNextTask(queueName), "Next task selecting time");

                    if (!nextTaskOpt.isPresent())
                    {
                        logger.trace("no task in queue: {}", queueName);
                        Thread.sleep(queueProperties.getNoTaskTimeoutMs());
                        return;

                    }
                    final RawTask rawTask = nextTaskOpt.get();

//                    final Span span = createSpan(rawTask);

                    try
                    {
                        final QueueAction queueAction = transactionTemplate.execute(action -> processRawTask(rawTask));
                        processQueueAction(queueAction, rawTask.getId(), rawTask.getAttempts());
                    }
                    catch (final Exception e)
                    {
                        logger.warn("error during queue process, re-enqueue with attempts increment: ", e);
                        processQueueAction(QueueAction.REENQUEUE_ON_ERROR, rawTask.getId(), rawTask.getAttempts());
                    }
//                    finally
//                    {
//                        tracer.close(span);
//                    }

                }
                catch (final Exception e)
                {
                    logger.warn("error during thread process: ", e);
                    status.setRollbackOnly();
                }
            }
        });
    }


//    private Optional<Span> getParentSpan(final RawTask rawTask)
//    {
//        if (rawTask.getParentSpan().isPresent())
//        {
//            final Span span = SerializationUtils.fromJson(rawTask.getParentSpan().get(), Span.class);
//            return Optional.of(span);
//        }
//        return Optional.empty();
//    }


//    private Span createSpan(final RawTask rawTask)
//    {
//        final Optional<Span> parent = getParentSpan(rawTask);
//        return parent.map(span -> tracer.createSpan("queue:/" + queueName, span))
//                .orElseGet(() -> tracer.createSpan("queue:/" + queueName));
//    }


    private QueueAction processRawTask(final RawTask rawTask)
    {
        final DataT data = deserializer.apply(rawTask.getData());

        final Task<DataT> task = Task.<DataT>builder()
                .withAttempts(rawTask.getAttempts())
                .withChangeDate(rawTask.getChangeDate())
                .withCreateDate(rawTask.getCreateDate())
                .withExecutionDate(rawTask.getExecutionDate())
                .withId(rawTask.getId())
                .withData(data)
                .build();

        logger.debug("process task: queue={}, {}", queueName, task);

        final QueueAction queueAction = log(() -> process(task), "Execution queue process time");

        logger.info("process task ended: queue={}, task={}, action={}", queueName, task, queueAction);
        return queueAction;
    }


    private void processQueueAction(final QueueAction queueAction, long taskId, int attempts)
    {
        switch (queueAction)
        {
            case DEQUEUE:
                dequeue(taskId);
                break;
            case REENQUEUE:
                reenqueue(taskId);
                break;
            case REENQUEUE_ON_ERROR:
                reenqueueOnError(taskId, attempts);
                break;
            default:
                throw new IllegalArgumentException("unsupported queue action '" + queueAction + "'");
        }
    }


    private void dequeue(final long taskId)
    {
        taskManager.remove(taskId);
    }


    private void reenqueue(final long taskId)
    {
        taskManager.updateExecutionDate(taskId, LocalDateTime.now().plus(REENQUEUE_DURATION));
    }


    private void reenqueueOnError(final long taskId, final int attempts)
    {
        taskManager.updateExecutionDateAndAttempts(taskId, getNextExecutionDate(attempts), attempts);
    }


    /**
     * Метод расчета времени следующего запуска задачи.
     *
     * @param attempts количество попыток.
     * @return Время следующего запуска.
     */
    private LocalDateTime getNextExecutionDate(final int attempts)
    {
        return LocalDateTime.now().plus(Duration.ofMillis((long) (Math.pow(REENQUEUE_DURATION.toMillis(), attempts))));
    }


    private <T> T log(final Supplier<T> supplier, final String message)
    {
        final long startTime = System.currentTimeMillis();
        final T t = supplier.get();
        final long stopTime = System.currentTimeMillis() - startTime;

        logger.trace("{}: {} ms", message, stopTime);
        return t;
    }
}
