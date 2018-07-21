package com.barysevich.project.commons.queue;

import com.barysevich.project.commons.queue.task.TaskManager;
import org.springframework.transaction.support.TransactionTemplate;

public class QueueComponentHolder
{
    private final TaskManager taskManager;

    private final TransactionTemplate transactionTemplate;

//    private final Tracer tracer;


    public QueueComponentHolder(
            final TaskManager taskManager,
            final TransactionTemplate transactionTemplate)
    {
        this.taskManager = taskManager;
        this.transactionTemplate = transactionTemplate;
//        this.tracer = tracer;
    }


    public TaskManager getTaskManager()
    {
        return taskManager;
    }


    public TransactionTemplate getTransactionTemplate()
    {
        return transactionTemplate;
    }


//    public Tracer getTracer()
//    {
//        return tracer;
//    }
}
