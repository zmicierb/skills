package com.barysevich.authorization.core.async;

import com.barysevich.authorization.api.async.AuthorizationRegistrationListener;
import com.barysevich.authorization.api.async.RegistrationResult;
import com.barysevich.authorization.core.queue.RegistrationQueue;
import com.barysevich.authorization.core.queue.RegistrationQueueData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Слушатель сообщений из кафки, которые имеют тип {@link RegistrationResult}.
 */
@Component
public class AuthorizationRegistrationListenerImpl implements AuthorizationRegistrationListener {

    private final RegistrationQueue registrationQueue;


    @Autowired
    public AuthorizationRegistrationListenerImpl(
            final RegistrationQueue registrationQueue) {
        this.registrationQueue = registrationQueue;
    }


    @Override
    public void onReceive(final RegistrationResult registrationResult) {
        final RegistrationQueueData registrationQueueData = RegistrationQueueData.create()
                .withId(registrationResult.getId())
                .withRegistrationServiceStatuses(registrationResult.getRegistrationStatus())
                .build();

        registrationQueue.enqueue(registrationQueueData);
    }
}
