package com.barysevich.project.api;


import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Перечисление всех типов доступных уведомлений,
 * в соответствии со значением перечисления выбирается процессор для обработки события
 * конкретным сервисом отправки сообщений.
 */
public enum NotificationType
{
    /**
     * Пользователь зарегистрировался.
     */
    USER_REGISTERED(100);


    private static final Map<Integer, NotificationType> TYPE_BY_CODE_MAP = Arrays.stream(NotificationType.values())
        .collect(Collectors.toMap(NotificationType::getCode, Function.identity()));

    private final int code;


    NotificationType(final int code)
    {
        this.code = code;
    }


    @JsonCreator
    public static NotificationType getByCode(final Integer code)
    {
        return Optional.ofNullable(TYPE_BY_CODE_MAP.get(code)).orElseThrow(
            () -> new IllegalArgumentException("NotificationType with code = " + code + "doesn't exist."));
    }


    @JsonValue
    public int getCode()
    {
        return code;
    }
}
