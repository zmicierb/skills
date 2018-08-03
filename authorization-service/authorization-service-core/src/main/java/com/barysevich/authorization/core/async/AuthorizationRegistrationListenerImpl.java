package com.barysevich.authorization.core.async;


import com.barysevich.authorization.api.async.AuthorizationRegistrationListener;
import com.barysevich.authorization.api.async.RegistrationInfoMessage;
import com.barysevich.authorization.core.queue.RegistrationQueue;
import com.barysevich.authorization.core.queue.RegistrationQueueData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Слушатель сообщений из кафки, которые имеют тип {@link RegistrationInfoMessage}.
 */
@Component
public class AuthorizationRegistrationListenerImpl implements AuthorizationRegistrationListener
{

    private final RegistrationQueue registrationQueue;


    @Autowired
    public AuthorizationRegistrationListenerImpl(
        final RegistrationQueue registrationQueue)
    {
        this.registrationQueue = registrationQueue;
    }


    @Override
    public void onReceive(final RegistrationInfoMessage registrationInfoMessage)
    {
        final RegistrationQueueData registrationQueueData = RegistrationQueueData.create()
            .withId(registrationInfoMessage.getId())
            .withEmail(registrationInfoMessage.getEmail())
            .build();

        registrationQueue.enqueue(registrationQueueData);
    }
}
