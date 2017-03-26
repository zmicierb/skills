package com.barysevich.project.repository;

import com.barysevich.project.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void findAllFromEmptyRepository() {
        Iterable<Person> persons = personRepository.findAll();

        assertThat(persons).isEmpty();
    }

    @Test
    public void save() {
        Person customer = personRepository.save(new Person("Jack"));

        assertThat(customer).hasFieldOrPropertyWithValue("name", "Jack");
    }

    @Test
    public void delete() {
        Person person1 = entityManager.persist(new Person("Jack"));
        Person person2 = entityManager.persist(new Person("Adam"));

        personRepository.delete(person1.getId());
        personRepository.delete(person2.getId());

        assertThat(personRepository.findAll()).isEmpty();
    }

    @Test
    public void findAll() {
        Person person1 = new Person("Jack");
        entityManager.persist(person1);

        Person person2 = new Person("Dima");
        entityManager.persist(person2);

        Person person3 = new Person("Kate");
        entityManager.persist(person3);


        Iterable<Person> customers = personRepository.findAll();

        assertThat(customers).contains(person1, person2, person3);
    }

    @Test
    public void findOne() {
        Person person1 = new Person("Jack");
        entityManager.persist(person1);

        Person person2 = new Person("Adam");
        entityManager.persist(person2);

        Person foundCustomer = personRepository.findOne(person2.getId());

        assertThat(foundCustomer).isEqualTo(person2);
    }

    @Test
    public void findByName() {
        Person person1 = entityManager.persist(new Person("Dima"));
        Person person2 = entityManager.persist(new Person("Kate"));

        Iterable<Person> persons = personRepository.findByNameContainingIgnoreCase("im");

        assertThat(persons).contains(person1);
        assertThat(persons).doesNotContain(person2);
    }

}