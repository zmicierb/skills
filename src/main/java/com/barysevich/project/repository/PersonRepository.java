package com.barysevich.project.repository;

import com.barysevich.project.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    List<Person> findByNameContainingIgnoreCase(String name);

    @Modifying
    @Query("UPDATE Person p SET p.deleted=1 WHERE p.id = :id ")
    void delete(@Param("id") Long id);

    @Query("SELECT p FROM Person p WHERE p.deleted<>1")
    Iterable<Person> findAll();

}
