package com.barysevich.authorization.kafka.exporter;


import com.barysevich.authorization.api.async.AuthorizationRegistrationExporter;
import com.barysevich.authorization.api.async.RegistrationInfoMessage;
import com.barysevich.project.kafka.api.MessageExporter;


/**
 * Кафка экспортер
 */
public class AuthorizationRegistrationKafkaExporter implements AuthorizationRegistrationExporter
{
    private final MessageExporter messageExporter;

    private final String resultTopic;


    public AuthorizationRegistrationKafkaExporter(
            final MessageExporter messageExporter, final String resultTopic)
    {
        this.messageExporter = messageExporter;
        this.resultTopic = resultTopic;
    }


    @Override
    public boolean exportRegistrationResult(final RegistrationInfoMessage registrationInfoMessage)
    {
        return messageExporter.export(resultTopic, registrationInfoMessage);
    }
}