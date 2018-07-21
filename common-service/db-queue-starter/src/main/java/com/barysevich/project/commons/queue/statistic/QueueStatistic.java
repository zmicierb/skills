package com.barysevich.project.commons.queue.statistic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * Статистика очередей.
 */
@Component
@ManagedResource(objectName = "Queue:name=manage")
public class QueueStatistic
{
    private static final Logger logger = LoggerFactory.getLogger(QueueStatistic.class);

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public QueueStatistic(final JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Возвращает общее количество задач в очередях.
     *
     * @return количество задач.
     */
    @ManagedAttribute
    public long getTotalCount()
    {
        logger.trace("getTotalCount()");

        final String SQL = "SELECT count(*) FROM tasks";
        final Long count = jdbcTemplate.queryForObject(SQL, Long.class);

        logger.trace("getTotalCount(): count={}", count);
        return count;
    }


    /**
     * Возвращает количество задач, число попыток запуска которых превышает заданное значение.
     *
     * @param attempts Число попыток.
     * @return Количество задач.
     */
    @ManagedOperation
    public long getCountByAttempts(final int attempts)
    {
        logger.trace("getCountByAttempts(): attempts={}", attempts);

        final String SQL = "SELECT count(*) FROM tasks WHERE attempts > ?";
        final Long count = jdbcTemplate.queryForObject(SQL, Long.class, attempts);

        logger.trace("getCountByAttempts(): count={}", count);
        return count;
    }


    /**
     * Сбрасывает количество попыток и время запуска у задач, число попыток которых превышает заданное значение.
     *
     * @param attempts Число попыток.
     */
    @ManagedOperation
    public void reset(int attempts)
    {
        logger.trace("reset(): attempts={}", attempts);

        final String SQL = "UPDATE tasks SET attempts = 0, execution_date = now() WHERE attempts > ?";
        jdbcTemplate.update(SQL, attempts);

        logger.trace("reset ended");
    }
}
