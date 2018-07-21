package com.barysevich.project.commons.queue;

/**
 * Возможные действия после выполнения очереди.
 */
public enum QueueAction
{
    /**
     * Переставить в очередь.
     */
    REENQUEUE,
    /**
     * Преставить в очередь, учитывая факт ошибки.
     */
    REENQUEUE_ON_ERROR,

    /**
     * Удалить из очереди.
     */
    DEQUEUE
}
