package com.barysevich.authorization.api.async;

public interface AuthorizationRegistrationExporter {
    /**
     * @param registrationResult - идентификатор, статус регистрации, код ошибки
     * @return true в случае успешной отправки сообщения
     */
    boolean exportRegistrationResult(final RegistrationResult registrationResult);
}
