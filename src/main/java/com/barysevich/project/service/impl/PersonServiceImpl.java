package com.barysevich.project.service.impl;

import com.barysevich.project.model.Department;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.Position;
import com.barysevich.project.repository.DepartmentRepository;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.repository.PositionRepository;
import com.barysevich.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public Person update(Long id, Person person) {
        Person update = personRepository.findOne(id);
        update.setName(person.getName());
        update.setBirthDate(person.getBirthDate());
        if (person.getPosition().isNew())
            update.setPosition(positionRepository.save(new Position(person.getPosition().getName())));
        else
            update.setPosition(person.getPosition());
        if (person.getDepartment().isNew())
            update.setDepartment(departmentRepository.save(new Department(person.getDepartment().getName())));
        else
            update.setDepartment(person.getDepartment());

        return update;
    }

}
