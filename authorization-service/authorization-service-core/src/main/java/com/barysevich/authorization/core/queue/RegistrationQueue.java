package com.barysevich.authorization.core.queue;

import com.barysevich.authorization.api.async.RegistrationStatus;
import com.barysevich.authorization.core.dao.RegistrationDAO;
import com.barysevich.project.commons.queue.BaseQueue;
import com.barysevich.project.commons.queue.QueueAction;
import com.barysevich.project.commons.queue.QueueBuilder;
import com.barysevich.project.commons.queue.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class RegistrationQueue extends BaseQueue<RegistrationQueueData> {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationQueue.class);

    private final RegistrationDAO registrationDAO;


    public RegistrationQueue(
            final QueueBuilder<RegistrationQueueData> queueBuilder,
            final RegistrationDAO registrationDAO) {
        super(queueBuilder);
        this.registrationDAO = registrationDAO;
    }


    @Override
    public QueueAction process(final Task<RegistrationQueueData> task) {
        final RegistrationQueueData registrationQueueData = task.getData();
        logger.debug("Registration queue income data={}", registrationQueueData);

        final Optional<RegistrationStatus> registrationStatus = registrationDAO.getRegistrationStatus(
                registrationQueueData.getId());

        if (registrationStatus.isPresent()) {
            logger.debug("Queue process : registration result already has status={}, {}",
                    registrationStatus.get(), registrationQueueData);
            return QueueAction.DEQUEUE;
        }

        final boolean isSuccessRegistration = registrationDAO.storeRegistrationStatus(
                registrationQueueData.getId(),
                registrationQueueData.getRegistrationStatus().getCode());

        if (!isSuccessRegistration) {
            return QueueAction.REENQUEUE_ON_ERROR;
        }

//        return processSuccessRegistrationResult(registrationQueueData);
        return QueueAction.DEQUEUE;
    }

//TODO add exporter to send mail
//    private QueueAction processSuccessRegistrationResult(
//            final RegistrationQueueData registrationQueueData)
//    {
//        logger.debug("Registration result is successful : {}", registrationQueueData);
//        if (!exporter.newCustomer(registrationQueueData.getId()))
//        {
//            return QueueAction.REENQUEUE_ON_ERROR;
//        }
//
//        return QueueAction.DEQUEUE;
//    }
}