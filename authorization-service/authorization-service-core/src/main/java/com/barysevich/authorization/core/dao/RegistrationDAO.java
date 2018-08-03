package com.barysevich.authorization.core.dao;


import com.barysevich.authorization.api.async.RegistrationStatus;

import java.sql.Timestamp;
import java.util.Optional;


public interface RegistrationDAO
{
    /**
     * @return резервирует и возвращает идентификатор при регистрации
     */
    Long reserveId();


    /**
     * Добавляет креды кастомеру по его идентификатору
     *
     * @param id - reserved id
     * @param login - логин
     * @param createdOn - дата создания
     */
    boolean addCredentials(final long id, final String login,
                           final Timestamp createdOn);

    /**
     * Записывает статус регистрации
     *
     * @param id     - идентификатор
     * @param status - статус регистрации
     */
    boolean storeRegistrationStatus(final long id, final int status);


    /**
     * @param id идентификатор
     * @return возвращает статус регистрации
     */
    Optional<RegistrationStatus> getRegistrationStatus(final long id);
}
