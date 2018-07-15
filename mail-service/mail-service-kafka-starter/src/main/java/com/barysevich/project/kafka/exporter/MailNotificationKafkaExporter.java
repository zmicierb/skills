package com.barysevich.project.kafka.exporter;


import com.barysevich.project.api.async.exporter.MailNotificationExporter;
import com.barysevich.project.api.model.MailNotificationMessage;
import com.barysevich.project.kafka.api.MessageExporter;


/**
 * Кафка экспортер
 */
public class MailNotificationKafkaExporter implements MailNotificationExporter
{
    private final MessageExporter messageExporter;

    private final String resultTopic;


    public MailNotificationKafkaExporter(
            final MessageExporter messageExporter, final String resultTopic)
    {
        this.messageExporter = messageExporter;
        this.resultTopic = resultTopic;
    }


    @Override
    public boolean exportMessage(final MailNotificationMessage message)
    {
        return messageExporter.export(resultTopic, message);
    }
}