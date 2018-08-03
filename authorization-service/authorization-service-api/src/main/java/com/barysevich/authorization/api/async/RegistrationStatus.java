package com.barysevich.authorization.api.async;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Статусы ответов от сервисов о процедуре регистрации.
 */
public enum RegistrationStatus {
    NEW(1),
    ACTIVE(2),
    BLOCKED(3);

    private static final Map<Integer, RegistrationStatus> STATUSES_MAP = Arrays.stream(RegistrationStatus.values())
            .collect(Collectors.toMap(RegistrationStatus::getCode, Function.identity()));

    private final int code;


    RegistrationStatus(final int code)
    {
        this.code = code;
    }


    public int getCode()
    {
        return code;
    }


    public static RegistrationStatus getByCode(final int code)
    {
        return Optional.ofNullable(STATUSES_MAP.get(code)).orElseThrow(
                () -> new IllegalArgumentException("unsupported service status '" + code + "'"));
    }
}
