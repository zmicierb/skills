package com.barysevich.project.kafka;


import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;


public class MessageWrapper<T>
{
    public static final String PAYLOAD_FIELD_NAME = "x-payload";

    public static final String HEADERS_FIELD_NAME = "x-headers";

    private final T payload;

    private final Map<String, String> headers;


    @JsonCreator
    private MessageWrapper(
            @JsonProperty(PAYLOAD_FIELD_NAME) final T payload,
            @JsonProperty(HEADERS_FIELD_NAME) final Map<String, String> headers)
    {
        this.payload = requireNonNull(payload);
        this.headers = requireNonNull(headers);
    }


    public static <T> MessageWrapper<T> of(final T payload, final Map<String, String> headers)
    {
        return new MessageWrapper<>(payload, headers);
    }


    public static <T> MessageWrapper<T> of(final T payload)
    {
        return new MessageWrapper<>(payload, Collections.emptyMap());
    }


    @JsonProperty(PAYLOAD_FIELD_NAME)
    public T getPayload()
    {
        return payload;
    }


    @JsonProperty(HEADERS_FIELD_NAME)
    public Map<String, String> getHeaders()
    {
        return headers;
    }


    @Override
    public String toString()
    {
        return "MessageWrapper{" +
                "payload=" + payload +
                ", headers=" + headers +
                '}';
    }
}
