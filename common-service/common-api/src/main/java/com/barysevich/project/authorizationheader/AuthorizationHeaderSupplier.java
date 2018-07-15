package com.barysevich.project.authorizationheader;


/**
 * Компонента, предоставляющая заголовок авторизации для текущей сессии
 */
public interface AuthorizationHeaderSupplier
{
    String authorizationHeader();
}
