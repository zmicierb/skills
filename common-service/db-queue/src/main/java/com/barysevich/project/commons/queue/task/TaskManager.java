package com.barysevich.project.commons.queue.task;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Менеджер работы с задачами.
 */
public interface TaskManager
{
    /**
     * Добавление задачи в очередь c обеспечением уникальности
     *
     * @param serializedData Данные задачи.
     * @param uuid Идентификатор задачи
     * @param properties {@link TaskProperties} параметры задачи
     */
    void addTask(final String serializedData, final UUID uuid, final TaskProperties properties);


    /**
     * Получение следующей задачи.
     *
     * @param queueName имя очереди.
     *
     * @return Опционально задача с данными.
     */
    Optional<RawTask> getNextTask(final String queueName);

    /**
     * Удалить задачу из очереди.
     *
     * @param taskId Идентификатор задачи.
     */
    void remove(long taskId);

    /**
     * Обновить время запуска.
     *
     * @param taskId           Идентификатор задачи.
     * @param newExecutionDate время следующего запуска
     */
    void updateExecutionDate(long taskId, final LocalDateTime newExecutionDate);

    /**
     * Обновить время запуска и инкрементировать количество попыток.
     *
     * @param taskId           Идентификатор задачи.
     * @param newExecutionDate время следующего запуска
     * @param attempts         Текущее количество попыток.
     */
    void updateExecutionDateAndAttempts(long taskId, final LocalDateTime newExecutionDate, int attempts);
}
