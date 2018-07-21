package com.barysevich.project.commons.queue;

import com.barysevich.project.commons.queue.statistic.QueueStatistic;
import com.barysevich.project.commons.queue.task.DefaultTaskManager;
import com.barysevich.project.commons.queue.task.TaskManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collection;

/**
 * Автоконфигурация для {@link QueueStarter}
 */
@Configuration
public class QueueStarterAutoConfiguration
{
    @Bean
    @ConditionalOnBean(BaseQueue.class)
    public QueueStarter queueStarter(final Collection<BaseQueue> queues)
    {
        return new QueueStarter(queues);
    }


    @Bean
    @ConditionalOnMissingBean(TaskManager.class)
    public TaskManager taskManager(final JdbcTemplate jdbcTemplate, final NamedParameterJdbcTemplate namedJt)
    {
        return new DefaultTaskManager(jdbcTemplate, namedJt);
    }


    @Bean
    @ConditionalOnMissingBean(QueueStatistic.class)
    public QueueStatistic queueStatistic(final JdbcTemplate jdbcTemplate)
    {
        return new QueueStatistic(jdbcTemplate);
    }


    @Bean
    @ConditionalOnMissingBean(QueueComponentHolder.class)
    public QueueComponentHolder queueComponentHolder(
            final TaskManager taskManager,
            final PlatformTransactionManager platformTransactionManager)
//            final Tracer tracer)
    {

        final TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        return new QueueComponentHolder(taskManager, transactionTemplate);
    }
}
