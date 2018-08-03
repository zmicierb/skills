package com.barysevich.authorization.api;


import com.barysevich.authorization.api.request.LoginRequest;


public interface AuthorizationService
{

    /**
     * @return резервирует и возвращает идентификатор при регистрации
     */
    Long reserveId();


    AuthorizationResult authenticate(final LoginRequest request);
}
