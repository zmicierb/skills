package com.barysevich.authorization.api.async;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationResult {
    private Long id;

    private RegistrationStatus registrationStatus;


    @JsonCreator
    private RegistrationResult(
            @JsonProperty("id") Long id,
            @JsonProperty("registrationStatus") RegistrationStatus registrationStatus) {
        this.id = id;
        this.registrationStatus = registrationStatus;
    }


    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("registrationStatus")
    public RegistrationStatus getRegistrationStatus() {
        return registrationStatus;
    }


    @Override
    public String toString() {
        return "RegistrationResult{" +
                "id=" + id +
                ", registrationStatus=" + registrationStatus +
                '}';
    }
}
