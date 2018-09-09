package com.barysevich.project.service.impl;

import com.barysevich.project.model.Person;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, String> implements PersonService
{

    final private PersonRepository personRepository;
//
//    @Autowired
//    private PositionService positionService;
//
//    @Autowired
//    private DepartmentService departmentService;
//
//    @Autowired
//    private SkillSumRepository skillSumRepository;
//
//    @Autowired
//    private AuthorizationService authorizationService;
//
//    @Autowired
//    private AuthorizationRegistrationExporter authorizationRegistrationExporter;
//
//    @Autowired
//    private MailNotificationExporter mailNotificationExporter;

    @Autowired
    public PersonServiceImpl(final PersonRepository repository)
    {
        super(repository);
        this.personRepository = repository;
    }

//    @Transactional
//    @Override
//    public void remove(Long id)
//    {
//        personRepository.remove(id);
//    }
//
//
//    @Override
//    public Page<Person> findByNameContainingIgnoreCase(String name, Pageable pageable)
//    {
//        return personRepository.findByNameContainingIgnoreCase(name, pageable);
//    }
//
//
//    @Override
//    public Iterable<Person> findByNameContainingIgnoreCaseForTest(String name)
//    {
//        return personRepository.findByNameContainingIgnoreCaseForTest(name);
//    }
//
//

//    @Transactional
    @Override
    public Person update(Person person)
    {
        return personRepository.save(person);
    }
//
//
//    public Map<Long, PersonSkillsDto> findSkillsById(Long id)
//    {
//        HashMap<Long, PersonSkillsDto> personSkillsDto = new HashMap<>();
//        skillSumRepository.findByPersonId(id).forEach(a -> {
//            if (personSkillsDto.containsKey(a.getRowId())) {
//                personSkillsDto.get(a.getRowId()).getSkills()
//                        .add(new SkillDto(a.getSkillId(), a.getSkill().getName(), a.getPosition()));
//            } else {
//                personSkillsDto.put(a.getRowId(), new PersonSkillsDto(a));
//            }
//        });
//
//        return personSkillsDto;
//    }
//
//
//    @Override
//    @Transactional(rollbackFor=Exception.class)
//    public Person save(Person person)
//    {
//        final long reservedId = authorizationService.reserveId();
//        person.setId(reservedId);
//        person.setDepartment(departmentService.save(person.getDepartment()));
//        person.setPosition(positionService.save(person.getPosition()));
//
//        final boolean isExported = authorizationRegistrationExporter.exportRegistrationResult(
//                RegistrationInfoMessage.builder()
//                        .withId(reservedId)
//                        .withEmail(new Email(person.getEmail()))
//                        .build());
//
//        // TODO move to auth service
//        mailNotificationExporter.exportMessage(MailNotificationMessage.builder()
//                .withEmail(new Email(person.getEmail()))
//                .withLocale(new Locale("EN"))
//                .withMessageData("test")
//                .withNotificationId(UUID.randomUUID())
//                .withNotificationType(NotificationType.USER_REGISTERED)
//                .build());
//
//        if (!isExported)
//        {
//            throw new RuntimeException();
//        }
//
//        return personRepository.save(person);
//    }
}
