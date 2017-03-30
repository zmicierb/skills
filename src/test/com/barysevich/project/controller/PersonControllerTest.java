package com.barysevich.project.controller;

import com.barysevich.project.model.Person;
import com.barysevich.project.repository.PersonRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Autowired
    private ObjectMapper mapper;

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
    public void save() throws IOException {

        String test = "Test4";

        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/person", new Person(test), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("data").path("name");
        assertEquals(name.asText(), test);
    }

    @Test
    public void find() throws IOException {

        String test = "Test";

        ResponseEntity<String> response =
                restTemplate.getForEntity("/api/person/find/name=" + test, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        JsonNode root = mapper.readTree(response.getBody());
        JsonNode data = root.path("data");
        data.forEach(d -> assertTrue(d.path("name").asText().toUpperCase().contains(test.toUpperCase())));
    }
}