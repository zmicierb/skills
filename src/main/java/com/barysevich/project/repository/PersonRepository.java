package com.barysevich.project.repository;

import com.barysevich.project.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE lower(p.name) like lower(concat('%',concat(:name, '%'))) and p.deleted<>1")
    Page<Person> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Person p SET p.deleted=1 WHERE p.id = :id ")
    void remove(@Param("id") Long id);

    @Query("SELECT p FROM Person p WHERE p.deleted<>1")
    Page<Person> findAll(Pageable pageable);

}
