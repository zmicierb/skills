package com.barysevich.project.controller;

import com.barysevich.project.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by dima on 3/26/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void save() {
        ResponseEntity<Person> responseEntity =
                restTemplate.postForEntity("/api/person", new Person("Test"), Person.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}