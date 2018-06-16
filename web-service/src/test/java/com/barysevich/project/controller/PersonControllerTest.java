//package com.barysevich.project.controller;
//
//import com.barysevich.project.model.*;
//import com.fasterxml.jackson.databind.JsonNode;
//import org.junit.Test;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
///**
// * Created by dima on 3/26/17.
// */
//public class PersonControllerTest extends PopulateDBTest {
//
//    @Test
//    public void findAll() throws IOException {
//
//        int page = 0;
//        int size = 5;
//
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("/api/person?page=" + page + "&size=" + size, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode root = mapper.readTree(response.getBody());
//        JsonNode data = root.path("data");
//
//        Page<Person> persons = (Page) personService.findAll(new PageRequest(page, size));
//
//        assertEquals(persons.getNumberOfElements(), data.size());
//    }
//
//    @Test
//    public void getById() throws IOException {
//
//        String test1 = "Test1";
//
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("/api/person/find/" + test1, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode root = mapper.readTree(response.getBody());
//        Long id = root.path("data").get(0).path("id").asLong();
//
//        Person person = personService.findOne(id);
//
//        response = restTemplate.getForEntity("/api/person/" + id.toString(), String.class);
//
//        root = mapper.readTree(response.getBody());
//        assertTrue(root.path("data").path("name").asText().equalsIgnoreCase(person.getName()));
//    }
//
//    @Test
//    public void save() throws IOException {
//
//        String test = "Test4";
//        String email = "test4@test.com";
//
//        Department department = departmentService.save(new Department(test));
//        Position position = positionService.save(new Position(test));
//
//        ResponseEntity<String> response =
//                restTemplate.postForEntity("/api/person", new Person(test,
//                        email,
//                        position,
//                        department,
//                        LocalDate.of(1970, Month.JANUARY, 1)), String.class);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        JsonNode root = mapper.readTree(response.getBody());
//        JsonNode name = root.path("data").path("name");
//        assertEquals(name.asText(), test);
//    }
//
//    @Test
//    public void updatePerson() throws IOException {
//
//        String test1 = "Test1";
//        String test2 = "TestUpdated";
//
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("/api/person/find/" + test1, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode root = mapper.readTree(response.getBody());
//        Long id = root.path("data").get(0).path("id").asLong();
//
//        Person person = personService.findOne(id);
//        person.setName(test2);
//
//        HttpEntity<Person> requestEntity = new HttpEntity<Person>(person);
//
//        response = restTemplate.exchange("/api/person/" + id.toString(), HttpMethod.PUT, requestEntity, String.class);
//
//        root = mapper.readTree(response.getBody());
//        assertTrue(root.path("data").path("name").asText().equalsIgnoreCase(test2));
//        // shouldn't change position name
//        assertFalse(root.path("data").path("position").path("name").asText().equalsIgnoreCase(test2));
//    }
//
//    @Test
//    public void delete() throws IOException {
//
//        String test1 = "Test3";
//
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("/api/person/find/" + test1, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode root = mapper.readTree(response.getBody());
//        Long id = root.path("data").get(0).path("id").asLong();
//
//        HttpEntity<Person> requestEntity = new HttpEntity<Person>(new Person());
//
//        response = restTemplate.exchange("/api/person/" + id.toString(), HttpMethod.DELETE, requestEntity, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        Person deletedPerson = personService.findOne(id);
//
//        assertEquals(deletedPerson.isDeleted(), true);
//    }
//
//    @Test
//    public void findByName() throws IOException {
//
//        String test = "Test";
//
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("/api/person/find/" + test, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode root = mapper.readTree(response.getBody());
//        JsonNode data = root.path("data");
//        data.forEach(d -> assertTrue(d.path("name").asText().toUpperCase().contains(test.toUpperCase())));
//    }
//
//    @Test
//    public void findSkillsById() throws IOException {
//
//        String test1 = "Test1";
//
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("/api/person/find/" + test1, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode root = mapper.readTree(response.getBody());
//        Long id = root.path("data").get(0).path("id").asLong();
//
//        response = restTemplate.getForEntity("/api/person/" + id.toString() + "/skills", String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode body = mapper.readTree(response.getBody());
//        JsonNode data = body.path("data");
//        data.forEach(d -> assertTrue(d.path("rowName").asText().equalsIgnoreCase(test)));
//        data.forEach(d -> assertTrue(d.path("skills").get(0).path("skillName").asText().equalsIgnoreCase(test)));
//    }
//
//    @Test
//    public void updatePersonSkills() throws IOException {
//        String test1 = "Test1";
//        String test2 = "TestUpdated";
//
//        int page = 0;
//        int size = 5;
//
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("/api/person/find/" + test1, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode root = mapper.readTree(response.getBody());
//        Long id = root.path("data").get(0).path("id").asLong();
//
//        Person person = personService.findOne(id);
//        Page<Row> rows = (Page) rowService.findAll(new PageRequest(page, size));
//        Row row = rows.getContent().get(0);
//
//        List<SkillSum> skillSums = (List) skillSumService.findByPersonId(id);
//        skillSums.forEach(skillSum -> {
//            skillSum.setSkill(skillService.findOne(skillSum.getSkillId()));
//            skillSum.setRow(rowService.findOne(skillSum.getRowId()));
//        });
//        skillSums.add(new SkillSum(person.getId(), new Skill(test2), row, 999, 2));
//
//        HttpEntity<List<SkillSum>> requestEntity = new HttpEntity<>(skillSums);
//
//        response = restTemplate.exchange("/api/person/" + id.toString() + "/skills", HttpMethod.PUT, requestEntity, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        List<SkillSum> skillSumsUpdated = (List) skillSumService.findByPersonId(id);
//        assertEquals(skillSums.size(), skillSumsUpdated.size());
//    }
//
//    @Test
//    public void findProjectsById() throws IOException {
//
//        String test1 = "Test1";
//
//        ResponseEntity<String> response =
//                restTemplate.getForEntity("/api/person/find/" + test1, String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode root = mapper.readTree(response.getBody());
//        Long id = root.path("data").get(0).path("id").asLong();
//
//        response = restTemplate.getForEntity("/api/person/" + id.toString() + "/projects", String.class);
//
//        assertEquals(response.getStatusCode(), HttpStatus.OK);
//
//        JsonNode body = mapper.readTree(response.getBody());
//        JsonNode data = body.path("data");
//        data.forEach(d -> assertTrue(d.path("companyInfo").path("name").asText().equalsIgnoreCase(test)));
//    }
//}