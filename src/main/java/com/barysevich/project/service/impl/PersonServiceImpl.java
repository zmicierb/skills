package com.barysevich.project.service.impl;

import com.barysevich.project.controller.dto.PersonSkillsDto;
import com.barysevich.project.controller.dto.SkillDto;
import com.barysevich.project.model.Department;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.Position;
import com.barysevich.project.repository.*;
import com.barysevich.project.service.PersonService;
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
    private PositionRepository positionRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private SkillSumRepository skillSumRepository;

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
        this.personRepository = repository;
    }

    @Override
    public void remove(Long id) {
        personRepository.remove(id);
    }

    @Override
    public Page<Person> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return personRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    @Transactional
    @Override
    public Person update(Long id, Person person) {
        Person update = personRepository.findOne(id);
        update.setName(person.getName());
        update.setBirthDate(person.getBirthDate());
        //additional checks due to editable typeahead
        if (person.getPosition().isNew()) {
            Position position = positionRepository.findByName(person.getPosition().getName());
            if (position == null)
                update.setPosition(positionRepository.save(new Position(person.getPosition().getName())));
            else
                update.setPosition(position);
        } else
            update.setPosition(person.getPosition());
        if (person.getDepartment().isNew()) {
            Department department = departmentRepository.findByName(person.getDepartment().getName());
            if (department == null)
                update.setDepartment(departmentRepository.save(new Department(person.getDepartment().getName())));
            else
                update.setDepartment(department);
        } else
            update.setDepartment(person.getDepartment());

        return update;
    }

    public Map<Long, PersonSkillsDto> findSkillsById(Long id) {
        HashMap<Long, PersonSkillsDto> personSkillsDto = new HashMap<>();
        skillSumRepository.findByPersonId(id).forEach(a -> {
            if (personSkillsDto.containsKey(a.getRowId())) {
                personSkillsDto.get(a.getRowId()).getSkills()
                        .add(new SkillDto(a.getSkillId(), a.getSkill().getName(), a.getWeight()));
            } else {
                personSkillsDto.put(a.getRowId(), new PersonSkillsDto(a));
            }
        });

        return personSkillsDto;
    }
}
