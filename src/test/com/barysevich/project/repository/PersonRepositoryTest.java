package com.barysevich.project.repository;

import com.barysevich.project.model.Department;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dima on 3/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    private Position position;
    private Department department;
    private Person person1;
    private Person person2;
    private Person person3;

    @Before
    public void populateDB() {

        position = entityManager.persist(new Position("test"));
        department = entityManager.persist(new Department("test"));

        person1 = new Person("Test1",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person1);

        person2 = new Person("Test2",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person2);

        person3 = new Person("Test3",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person3);
    }

    @Test
    public void save() {
        Person customer = personRepository.save(new Person("Jack",
                new Position("test"),
                new Department("test"),
                LocalDate.of(1970, Month.JANUARY, 1)));

        assertThat(customer).hasFieldOrPropertyWithValue("name", "Jack");
    }

    @Test
    public void remove() {

        personRepository.remove(person1.getId());
        personRepository.remove(person2.getId());

        assertThat(personRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20))).doesNotContain(person1);
        assertThat(personRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20))).doesNotContain(person2);
    }

    @Test
    public void findAll() {

        Iterable<Person> customers = personRepository.findAll();

        assertThat(customers).contains(person1, person2, person3);
    }

    @Test
    public void findOne() {

        Person foundCustomer = personRepository.findOne(person2.getId());

        assertThat(foundCustomer).isNotEqualTo(person1);
        assertThat(foundCustomer).isEqualTo(person2);
    }

    @Test
    public void findByNameContainingIgnoreCase() {

        Iterable<Person> persons = personRepository.findByNameContainingIgnoreCase("ST1", new PageRequest(0, 20));

        assertThat(persons).contains(person1);
        assertThat(persons).doesNotContain(person2);
    }

}