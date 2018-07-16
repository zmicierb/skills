package com.barysevich.project.core.processor.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class NotificationExportLogPostgres implements NotificationExportLog
{
    private final JdbcTemplate jt;


    public NotificationExportLogPostgres(final JdbcTemplate jt)
    {
        this.jt = jt;
    }


    @Override
    public boolean isAlreadyProcessed(final UUID notification_id)
    {
        final String sql =
                "SELECT count(notification_id) " +
                        "FROM mail_service_export_log " +
                        "WHERE notification_id = ?";
        return jt.queryForObject(sql, Integer.class, notification_id) == 1;
    }


    @Override
    public void log(final UUID notification_id)
    {
        final String sql = "INSERT INTO mail_service_export_log (notification_id) VALUES (?) ON CONFLICT DO NOTHING";
        jt.update(sql, notification_id);
    }
}
