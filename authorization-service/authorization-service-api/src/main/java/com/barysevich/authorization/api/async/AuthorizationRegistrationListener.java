package com.barysevich.authorization.api.async;

public interface AuthorizationRegistrationListener {
    /**
     * Слушатель ответов на регистрацию
     * @param registrationInfoMessage - идентификатор, email
     */
    void onReceive(final RegistrationInfoMessage registrationInfoMessage);
}
