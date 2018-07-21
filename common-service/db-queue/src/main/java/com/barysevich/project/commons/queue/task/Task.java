package com.barysevich.project.commons.queue.task;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

/**
 * Объект задачи.
 */
public class Task<DataT>
{
    private final long id;

    private final LocalDateTime createDate;

    private final LocalDateTime changeDate;

    private final LocalDateTime executionDate;

    private final int attempts;

    private final DataT data;


    public long getId()
    {
        return id;
    }


    public LocalDateTime getCreateDate()
    {
        return createDate;
    }


    public LocalDateTime getChangeDate()
    {
        return changeDate;
    }


    public LocalDateTime getExecutionDate()
    {
        return executionDate;
    }


    public int getAttempts()
    {
        return attempts;
    }


    public DataT getData()
    {
        return data;
    }


    private Task(final Builder<DataT> builder)
    {
        id = requireNonNull(builder.id);
        createDate = requireNonNull(builder.createDate);
        changeDate = requireNonNull(builder.changeDate);
        executionDate = requireNonNull(builder.executionDate);
        attempts = requireNonNull(builder.attempts);
        data = requireNonNull(builder.data);
    }


    public static <DataT> Builder<DataT> builder()
    {
        return new Builder<>();
    }


    @Override
    public String toString()
    {
        return "Task{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", changeDate=" + changeDate +
                ", executionDate=" + executionDate +
                ", attempts=" + attempts +
                ", data='" + data + '\'' +
                '}';
    }


    public static final class Builder<DataT>
    {
        private Long id;

        private LocalDateTime createDate;

        private LocalDateTime changeDate;

        private LocalDateTime executionDate;

        private Integer attempts;

        private DataT data;


        private Builder()
        {
        }


        public Builder<DataT> withId(final Long val)
        {
            this.id = val;
            return this;
        }


        public Builder<DataT> withCreateDate(final LocalDateTime val)
        {
            this.createDate = val;
            return this;
        }


        public Builder<DataT> withChangeDate(final LocalDateTime val)
        {
            this.changeDate = val;
            return this;
        }


        public Builder<DataT> withExecutionDate(final LocalDateTime val)
        {
            this.executionDate = val;
            return this;
        }


        public Builder<DataT> withAttempts(final Integer val)
        {
            this.attempts = val;
            return this;
        }


        public Builder<DataT> withData(final DataT val)
        {
            this.data = val;
            return this;
        }


        public Task<DataT> build()
        {
            return new Task<>(this);
        }
    }
}
