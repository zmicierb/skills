package com.barysevich.project.commons.queue.task;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

/**
 * Объект задачи c данными в строковом представлении.
 */
public class RawTask
{
    private final long id;

    private final LocalDateTime createDate;

    private final LocalDateTime changeDate;

    private final LocalDateTime executionDate;

    private final int attempts;

    private final String data;

//    private final String parentSpan;


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


    public String getData()
    {
        return data;
    }


//    public Optional<String> getParentSpan()
//    {
//        return Optional.ofNullable(parentSpan);
//    }


    private RawTask(final Builder builder)
    {
        id = requireNonNull(builder.id);
        createDate = requireNonNull(builder.createDate);
        changeDate = requireNonNull(builder.changeDate);
        executionDate = requireNonNull(builder.executionDate);
        attempts = requireNonNull(builder.attempts);
        data = requireNonNull(builder.data);
//        parentSpan = builder.parentSpan;
    }


    public static Builder builder()
    {
        return new Builder();
    }


    @Override
    public String toString() {
        return "RawTask{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", changeDate=" + changeDate +
                ", executionDate=" + executionDate +
                ", attempts=" + attempts +
                ", data='" + data + '\'' +
                '}';
    }

    public static final class Builder
    {
        private Long id;

        private LocalDateTime createDate;

        private LocalDateTime changeDate;

        private LocalDateTime executionDate;

        private Integer attempts;

        private String data;

//        private String parentSpan;


        private Builder()
        {
        }


        public Builder withId(final Long val)
        {
            this.id = val;
            return this;
        }


        public Builder withCreateDate(final LocalDateTime val)
        {
            this.createDate = val;
            return this;
        }


        public Builder withChangeDate(final LocalDateTime val)
        {
            this.changeDate = val;
            return this;
        }


        public Builder withExecutionDate(final LocalDateTime val)
        {
            this.executionDate = val;
            return this;
        }


        public Builder withAttempts(final Integer val)
        {
            this.attempts = val;
            return this;
        }


        public Builder withData(final String val)
        {
            this.data = val;
            return this;
        }


//        public Builder withParentSpan(final String val)
//        {
//            this.parentSpan = val;
//            return this;
//        }


        public RawTask build()
        {
            return new RawTask(this);
        }
    }
}
