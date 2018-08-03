package com.barysevich.authorization.core.queue;


import com.barysevich.project.email.Email;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class RegistrationQueueData
{
    private final Long id;

    private final Email email;


    @JsonCreator
    private RegistrationQueueData(
        @JsonProperty("email") Email email,
        @JsonProperty("id") Long id)
    {
        this.email = email;
        this.id = id;
    }


    public static Builder create()
    {
        return new Builder();
    }


    @JsonProperty("email")
    public Email getEmail() {
        return email;
    }


    @JsonProperty("id")
    public Long getId()
    {
        return id;
    }


    @Override
    public String toString() {
        return "RegistrationQueueData{" +
                "id=" + id +
                ", email=" + email +
                '}';
    }

    public static class Builder
    {
        private Email email;

        private Long id;


        private Builder()
        {
        }


        public Builder withId(final Long id)
        {
            this.id = id;
            return this;
        }


        public Builder withEmail(final Email email)
        {
            this.email = email;
            return this;
        }


        public RegistrationQueueData build()
        {
            return new RegistrationQueueData(email, id);
        }
    }
}
