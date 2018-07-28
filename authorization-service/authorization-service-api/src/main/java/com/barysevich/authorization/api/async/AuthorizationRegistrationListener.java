package com.barysevich.authorization.api.async;

public interface AuthorizationRegistrationListener {
    /**
     * Слушатель ответов по регистрации от сервиса авторизации
     * @param registrationResult - идентификатор, статус, код ошибки
     */
    void onReceive(final RegistrationResult registrationResult);
}
