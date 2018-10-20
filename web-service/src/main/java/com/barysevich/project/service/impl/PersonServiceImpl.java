package com.barysevich.project.service.impl;


import com.barysevich.authorization.api.AuthorizationService;
import com.barysevich.authorization.api.async.AuthorizationRegistrationExporter;
import com.barysevich.authorization.api.async.RegistrationInfoMessage;
import com.barysevich.project.api.NotificationType;
import com.barysevich.project.api.async.exporter.MailNotificationExporter;
import com.barysevich.project.api.model.MailNotificationMessage;
import com.barysevich.project.email.Email;
import com.barysevich.project.localization.Locale;
import com.barysevich.project.model.Person;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, String> implements PersonService
{
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;

    private final AuthorizationService authorizationService;

    private final AuthorizationRegistrationExporter authorizationRegistrationExporter;

    private final MailNotificationExporter mailNotificationExporter;


    @Autowired
    public PersonServiceImpl(final PersonRepository repository,
                             final AuthorizationService authorizationService,
                             final AuthorizationRegistrationExporter authorizationRegistrationExporter,
                             final MailNotificationExporter mailNotificationExporter)
    {
        super(repository);
        this.personRepository = repository;
        this.authorizationService = authorizationService;
        this.authorizationRegistrationExporter = authorizationRegistrationExporter;
        this.mailNotificationExporter = mailNotificationExporter;
    }

//    @Transactional
    @Override
    public Person update(Person person)
    {
        if (person.getId() == null)
        {
            final long reservedId = authorizationService.reserveId();

            final boolean isExported = authorizationRegistrationExporter.exportRegistrationResult(
                RegistrationInfoMessage.builder()
                        .withId(reservedId)
                        .withEmail(new Email(person.getEmail()))
                        .build());

            if (!isExported)
            {
                logger.info("Auth registration export for reservedId={} is failed", reservedId);
                return null;
            }

            // TODO move to auth service
            mailNotificationExporter.exportMessage(MailNotificationMessage.builder()
                    .withEmail(new Email(person.getEmail()))
                    .withLocale(new Locale("EN"))
                    .withMessageData("test")
                    .withNotificationId(UUID.randomUUID())
                    .withNotificationType(NotificationType.USER_REGISTERED)
                    .build());

            return personRepository.save(Person.builder()
                    .withPerson(person)
                    .withPersonId(reservedId)
                    .build());
        }

        return personRepository.save(person);
    }


    @Override
    public Person findByPersonId(final Long personId)
    {
        return personRepository.findByPersonId(personId).orElse(null);
    }
}
