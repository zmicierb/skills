package com.barysevich.project.controller;

import com.barysevich.project.model.Department;
import com.barysevich.project.model.Person;
import com.barysevich.project.model.Position;
import com.barysevich.project.service.DepartmentService;
import com.barysevich.project.service.PersonService;
import com.barysevich.project.service.PositionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

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
    private PersonService personService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void populateDB() {

        Department department = departmentService.save(new Department("test"));
        Position position = positionService.save(new Position("test"));

        Arrays.asList(
                "Test1,Test2,Test3".split(","))
                .forEach(
                        a -> {
                            personService.save(new Person(a,
                                    position,
                                    department,
                                    LocalDate.of(1970, Month.JANUARY, 1)));
                        }
                );
    }

    @After
    public void cleanDB() {

        Iterable<Person> persons = personService.findByNameContainingIgnoreCase("test", new PageRequest(0, 10));

        persons.forEach(
                a -> {
                    personService.delete(a.getId());
                }
        );

        Iterable<Department> departments = departmentService.findByNameContainingIgnoreCase("test", new PageRequest(0, 10));

        departments.forEach(
                a -> {
                    departmentService.delete(a.getId());
                }
        );

        Iterable<Position> positions = positionService.findByNameContainingIgnoreCase("test", new PageRequest(0, 10));

        positions.forEach(
                a -> {
                    positionService.delete(a.getId());
                }
        );
    }

    @Test
    public void getById() throws IOException {

        String test1 = "Test1";

        ResponseEntity<String> response =
                restTemplate.getForEntity("/api/person/find/" + test1, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        JsonNode root = mapper.readTree(response.getBody());
        Long id = root.path("data").get(0).path("id").asLong();

        Person person = personService.findOne(id);

        HttpEntity<Person> requestEntity = new HttpEntity<Person>(person);

        response = restTemplate.getForEntity("/api/person/" + id.toString(), String.class);

        root = mapper.readTree(response.getBody());
        assertTrue(root.path("data").path("name").asText().equalsIgnoreCase(person.getName()));
    }

    @Test
    public void save() throws IOException {

        String test = "Test4";

        Department department = departmentService.save(new Department("test"));
        Position position = positionService.save(new Position("test"));

        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/person", new Person(test,
                        position,
                        department,
                        LocalDate.of(1970, Month.JANUARY, 1)), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("data").path("name");
        assertEquals(name.asText(), test);
    }

    @Test
    public void findByName() throws IOException {

        String test = "Test";

        ResponseEntity<String> response =
                restTemplate.getForEntity("/api/person/find/" + test, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        JsonNode root = mapper.readTree(response.getBody());
        JsonNode data = root.path("data");
        data.forEach(d -> assertTrue(d.path("name").asText().toUpperCase().contains(test.toUpperCase())));
    }

    @Test
    public void update() throws IOException {

        String test1 = "Test1";
        String test2 = "TestUpdated";

        ResponseEntity<String> response =
                restTemplate.getForEntity("/api/person/find/" + test1, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        JsonNode root = mapper.readTree(response.getBody());
        Long id = root.path("data").get(0).path("id").asLong();

        Person person = personService.findOne(id);
        person.setName(test2);

        HttpEntity<Person> requestEntity = new HttpEntity<Person>(person);

        response = restTemplate.exchange("/api/person/" + id.toString(), HttpMethod.PUT, requestEntity, String.class);

        root = mapper.readTree(response.getBody());
        assertTrue(root.path("data").path("name").asText().equalsIgnoreCase(test2));
    }

    @Test
    public void delete() throws IOException {

        String test1 = "Test3";

        ResponseEntity<String> response =
                restTemplate.getForEntity("/api/person/find/" + test1, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        JsonNode root = mapper.readTree(response.getBody());
        Long id = root.path("data").get(0).path("id").asLong();

        HttpEntity<Person> requestEntity = new HttpEntity<Person>(new Person());

        response = restTemplate.exchange("/api/person/" + id.toString(), HttpMethod.DELETE, requestEntity, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        //for cleaning DB
        personService.delete(id);
    }
}