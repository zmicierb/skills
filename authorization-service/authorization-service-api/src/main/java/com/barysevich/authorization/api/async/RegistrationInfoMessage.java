package com.barysevich.authorization.api.async;

import com.barysevich.project.email.Email;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;

public class RegistrationInfoMessage {
    private final Long id;

    private final Email email;


    @JsonCreator
    private RegistrationInfoMessage(
            @JsonProperty("id") final Long id,
            @JsonProperty("email") final Email email) {
        this.id = requireNonNull(id);
        this.email = requireNonNull(email);
    }


    public static Builder builder()
    {
        return new Builder();
    }


    @JsonProperty("id")
    public Long getId() {
        return id;
    }


    @JsonProperty("email")
    public Email getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "RegistrationInfoMessage{" +
                "id=" + id +
                ", email=" + email +
                '}';
    }


    public static final class Builder
    {
        private Long id;

        private Email email;



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


        public RegistrationInfoMessage build()
        {
            return new RegistrationInfoMessage(id, email);
        }
    }
}
