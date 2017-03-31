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
    private PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        super(repository);
        this.repository = repository;
    }

}
