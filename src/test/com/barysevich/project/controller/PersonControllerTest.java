package com.barysevich.project.controller;

import com.barysevich.project.controller.dto.Response;
import com.barysevich.project.model.Person;
import com.barysevich.project.repository.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by dima on 3/26/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void populateDB() {
        Arrays.asList(
                "Test1,Test2,Test3".split(","))
                .forEach(
                        a -> {
                            Person person = personRepository.save(new Person(a));
                        }
                );
    }

    @After
    public void cleanDB() {

        List<Person> persons = personRepository.findByNameContainingIgnoreCase("test");

        persons.forEach(
                a -> {
                    personRepository.delete(a.getId());
                }
        );
    }

    @Test
    public void save() {
        ResponseEntity<Response> responseEntity =
                restTemplate.postForEntity("/api/person", new Person("Test4"), Response.class);

        Response person = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(person.getName(), "Test4");
    }


}