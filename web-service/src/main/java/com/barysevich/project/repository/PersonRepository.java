package com.barysevich.project.repository;

import com.barysevich.project.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PersonRepository extends PagingAndSortingRepository<Person, String>
{
    Optional<Person> findByPersonId(final Long personId);
}
