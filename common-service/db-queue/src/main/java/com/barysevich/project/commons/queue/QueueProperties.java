package com.barysevich.project.commons.queue;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static java.util.Objects.requireNonNull;

/**
 * Настройки очереди.
 */
@ConfigurationProperties(prefix = "skills.queue")
public class QueueProperties
{
    private Long betweenTaskTimeoutMs;

    private Integer threadPoolSize;

    private Long noTaskTimeoutMs;


    public QueueProperties()
    {
    }


    private QueueProperties(final Builder builder)
    {
        betweenTaskTimeoutMs = requireNonNull(builder.betweenTaskTimeoutMs);
        threadPoolSize = requireNonNull(builder.threadPoolSize);
        noTaskTimeoutMs = requireNonNull(builder.noTaskTimeoutMs);
    }


    public static Builder builder()
    {
        return new Builder();
    }


    public Long getBetweenTaskTimeoutMs()
    {
        return betweenTaskTimeoutMs;
    }


    public Integer getThreadPoolSize()
    {
        return threadPoolSize;
    }


    public Long getNoTaskTimeoutMs()
    {
        return noTaskTimeoutMs;
    }


    public void setBetweenTaskTimeoutMs(final Long betweenTaskTimeoutMs)
    {
        this.betweenTaskTimeoutMs = betweenTaskTimeoutMs;
    }


    public void setThreadPoolSize(final Integer threadPoolSize)
    {
        this.threadPoolSize = threadPoolSize;
    }


    public void setNoTaskTimeoutMs(final Long noTaskTimeoutMs)
    {
        this.noTaskTimeoutMs = noTaskTimeoutMs;
    }


    @Override
    public String toString()
    {
        return "QueueProperties{" +
                "betweenTaskTimeoutMs=" + betweenTaskTimeoutMs +
                ", threadPoolSize=" + threadPoolSize +
                ", noTaskTimeoutMs=" + noTaskTimeoutMs +
                '}';
    }


    public static final class Builder
    {
        private Long betweenTaskTimeoutMs;

        private Integer threadPoolSize;

        private Long noTaskTimeoutMs;


        private Builder()
        {
        }


        public Builder withBetweenTaskTimeoutMs(final Long val)
        {
            this.betweenTaskTimeoutMs = val;
            return this;
        }


        public Builder withThreadPoolSize(final Integer val)
        {
            this.threadPoolSize = val;
            return this;
        }


        public Builder withNoTaskTimeoutMs(final Long val)
        {
            this.noTaskTimeoutMs = val;
            return this;
        }


        public QueueProperties build()
        {
            return new QueueProperties(this);
        }
    }
}
