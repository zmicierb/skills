package com.barysevich.project.service;

import com.barysevich.project.model.Person;

public interface PersonService extends GenericService<Person, String>
{
//    void remove(Long id);

//    Page<Person> findByNameContainingIgnoreCase(String name, Pageable pageable);

//    Iterable<Person> findByNameContainingIgnoreCaseForTest(String name);

    Person update(Person person);

//    Map<Long, PersonSkillsDto> findSkillsById(Long id);
}
