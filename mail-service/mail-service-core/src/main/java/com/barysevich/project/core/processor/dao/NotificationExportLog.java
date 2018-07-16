package com.barysevich.project.core.processor.dao;

import java.util.UUID;

/**
 * Логгирует успешно обработанные уведомления {@link NotificationData}.
 */
public interface NotificationExportLog
{
    /**
     * Проверяет было ли обработано уведомление.
     *
     * @param notification_id идентификатор уведомления.
     * @return true, если экспортировано упешно.
     */
    boolean isAlreadyProcessed(final UUID notification_id);


    /**
     * Логирует успешно обраобтанное уведомление.
     *
     * @param notification_id идентификатор уведомления.
     */
    void log(final UUID notification_id);
}
