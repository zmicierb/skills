package com.barysevich.project.service;

import com.barysevich.project.controller.dto.PersonSkillsDto;
import com.barysevich.project.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by BarysevichD on 2017-03-31.
 */
public interface PersonService extends GenericService<Person, Long> {

    void remove(Long id);

    Page<Person> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Person update(Long id, Person person);

    Map<Long, PersonSkillsDto> findSkillsById(Long id);
}
