package com.barysevich.project.kafka.api;

public interface MessageExporter
{
    /**
     * Экспорт объекта
     *
     * @param topic топик
     * @param value экспортируемый объект
     * @return true если успешно
     */
    <T> boolean export(final String topic, final T value);


    /**
     * Экспорт объекта
     *
     * @param topic топик
     * @param key ключ
     * @param value экспортируемый объект
     * @return true если успешно
     */
    <T> boolean export(final String topic, final String key, final T value);
}
