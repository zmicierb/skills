package com.barysevich.project.repository;

import com.barysevich.project.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, String>
{

}
