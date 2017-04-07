package com.barysevich.project.service;

import com.barysevich.project.model.Person;

/**
 * Created by BarysevichD on 2017-03-31.
 */
public interface PersonService extends GenericService<Person, Long> {

    void remove(Long id);

    Iterable<Person> findByNameContainingIgnoreCase(String name);
}
