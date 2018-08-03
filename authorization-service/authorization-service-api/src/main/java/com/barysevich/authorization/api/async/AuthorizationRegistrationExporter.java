package com.barysevich.authorization.api.async;

public interface AuthorizationRegistrationExporter {
    /**
     * @param registrationInfoMessage - идентификатор, email
     * @return true в случае успешной отправки сообщения
     */
    boolean exportRegistrationResult(final RegistrationInfoMessage registrationInfoMessage);
}
