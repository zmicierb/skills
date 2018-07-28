package com.barysevich.authorization.core.queue;

import com.barysevich.authorization.api.async.RegistrationStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationQueueData {
    private final RegistrationStatus registrationStatus;

    private final Long id;

    @JsonCreator
    private RegistrationQueueData(
            @JsonProperty("registrationStatus") RegistrationStatus registrationStatus,
            @JsonProperty("id") Long id) {
        this.registrationStatus = registrationStatus;
        this.id = id;
    }

    public static Builder create()
    {
        return new Builder();
    }

    @JsonProperty("registrationStatus")
    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "RegistrationQueueData{" +
                "registrationStatus=" + registrationStatus +
                ", id=" + id +
                '}';
    }


    public static class Builder
    {
        private RegistrationStatus registrationStatus;

        private Long id;


        private Builder()
        {
        }


        public Builder withId(final Long id)
        {
            this.id = id;
            return this;
        }


        public Builder withRegistrationServiceStatuses(final RegistrationStatus registrationStatus)
        {
            this.registrationStatus = registrationStatus;
            return this;
        }


        public RegistrationQueueData build()
        {
            return new RegistrationQueueData(registrationStatus, id);
        }
    }
}
