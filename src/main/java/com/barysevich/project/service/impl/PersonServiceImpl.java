package com.barysevich.project.service.impl;

import com.barysevich.project.controller.dto.PersonSkillsDto;
import com.barysevich.project.controller.dto.SkillDto;
import com.barysevich.project.model.Person;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.repository.SkillSumRepository;
import com.barysevich.project.service.DepartmentService;
import com.barysevich.project.service.PersonService;
import com.barysevich.project.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, Long> implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SkillSumRepository skillSumRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
        this.personRepository = repository;
    }

    @Transactional
    @Override
    public void remove(Long id) {
        personRepository.remove(id);
    }

    @Override
    public Page<Person> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return personRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public Iterable<Person> findByNameContainingIgnoreCaseForTest(String name) {
        return personRepository.findByNameContainingIgnoreCaseForTest(name);
    }

    @Transactional
    @Override
    public Person update(Long id, Person person) {
        Person update = personRepository.findOne(id);
        update.setName(person.getName());
        update.setEmail(person.getEmail());
        update.setBirthDate(person.getBirthDate());
        update.setPosition(positionService.save(person.getPosition()));
        update.setDepartment(departmentService.save(person.getDepartment()));

        return personRepository.save(update);
    }

    public Map<Long, PersonSkillsDto> findSkillsById(Long id) {
        HashMap<Long, PersonSkillsDto> personSkillsDto = new HashMap<>();
        skillSumRepository.findByPersonId(id).forEach(a -> {
            if (personSkillsDto.containsKey(a.getRowId())) {
                personSkillsDto.get(a.getRowId()).getSkills()
                        .add(new SkillDto(a.getSkillId(), a.getSkill().getName(), a.getPosition()));
            } else {
                personSkillsDto.put(a.getRowId(), new PersonSkillsDto(a));
            }
        });

        return personSkillsDto;
    }

    @Override
    @Transactional
    public Person save(Person person) {
        person.setDepartment(departmentService.save(person.getDepartment()));
        person.setPosition(positionService.save(person.getPosition()));
        return personRepository.save(person);
    }
}
