package com.barysevich.project.service;

import com.barysevich.project.model.Person;

public interface PersonService extends GenericService<Person, String>
{
    Person update(final Person person);

    Person findByPersonId(final Long personId);
}
