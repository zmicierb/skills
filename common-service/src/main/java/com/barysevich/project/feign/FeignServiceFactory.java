package com.barysevich.project.feign;


/**
 * Компонента, позволяющая по интерфейсу сервиса и его адресу собрать Feign-интерфейс.
 */
public interface FeignServiceFactory
{
    <T> T build(final Class<T> clazz, final String url);


    <T> T buildWithoutAuth(final Class<T> clazz, final String url);
}
