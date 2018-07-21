--liquibase format sql

--changeset barysevich:mail_service_export_log_table

CREATE TABLE mail_service_export_log
(
  notification_id UUID NOT NULL,
  CONSTRAINT mail_service_export_log_notification_id_key UNIQUE (notification_id)
);
