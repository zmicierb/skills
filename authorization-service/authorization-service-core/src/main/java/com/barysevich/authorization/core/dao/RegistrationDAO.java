package com.barysevich.authorization.core.dao;

import com.barysevich.authorization.api.async.RegistrationStatus;

import java.util.Optional;

public interface RegistrationDAO {
    /**
     * Записывает статус регистрации кастомера определенного сервиса
     * @param id - идентификатор
     * @param status - статус регистрации
     */
    boolean storeRegistrationStatus(final long id, final int status);


    /**
     * @param id идентификатор кастомера
     * @return возвращает статус регистрации кастомера
     */
    Optional<RegistrationStatus> getRegistrationStatus(final long id);
}
