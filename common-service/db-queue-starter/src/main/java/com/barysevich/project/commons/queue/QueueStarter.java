package com.barysevich.project.commons.queue;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Collection;

/**
 * Стартует очереди после старта приложения.
 */
public class QueueStarter implements ApplicationListener<ContextRefreshedEvent>
{

    private final Collection<BaseQueue> queues;


    public QueueStarter(final Collection<BaseQueue> queues)
    {
        this.queues = queues;
    }


    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        queues.forEach(BaseQueue::start);
    }
}
