package com.barysevich.project.repository;

import com.barysevich.project.model.Department;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void save() {
        Person customer = personRepository.save(new Person("Jack",
                new Position("test"),
                new Department("test"),
                LocalDate.of(1970, Month.JANUARY, 1)));

        assertThat(customer).hasFieldOrPropertyWithValue("name", "Jack");
    }

    @Test
    public void delete() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));

        Person person1 = entityManager.persist(new Person("Test1",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1)));
        Person person2 = entityManager.persist(new Person("Test2",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1)));

        personRepository.remove(person1.getId());
        personRepository.remove(person2.getId());

        assertThat(personRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20))).doesNotContain(person1);
        assertThat(personRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20))).doesNotContain(person2);
    }

    @Test
    public void findAll() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));

        Person person1 = new Person("Test1",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person1);

        Person person2 = new Person("Test2",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person2);

        Person person3 = new Person("Test3",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person3);


        Iterable<Person> customers = personRepository.findAll();

        assertThat(customers).contains(person1, person2, person3);
    }

    @Test
    public void findOne() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));

        Person person1 = new Person("Test1",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person1);

        Person person2 = new Person("Test2",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person2);

        Person foundCustomer = personRepository.findOne(person2.getId());

        assertThat(foundCustomer).isEqualTo(person2);
    }

    @Test
    public void findByName() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));

        Person person1 = new Person("Test1",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person1);

        Person person2 = new Person("Test2",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1));
        entityManager.persist(person2);

        Iterable<Person> persons = personRepository.findByNameContainingIgnoreCase("ST1", new PageRequest(0, 20));

        assertThat(persons).contains(person1);
        assertThat(persons).doesNotContain(person2);
    }

}