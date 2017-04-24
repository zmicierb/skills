package com.barysevich.project.service.impl;

import com.barysevich.project.model.Person;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BarysevichD on 2017-03-31.
 */
@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, Long> implements PersonService {

    @Autowired
    private PersonRepository personRepository;

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
    public Iterable<Person> findByNameContainingIgnoreCase(String name) {
        return personRepository.findByNameContainingIgnoreCase(name);
    }
}
