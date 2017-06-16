package com.barysevich.project.controller;

import com.barysevich.project.model.Person;
import com.barysevich.project.model.Position;
import com.barysevich.project.model.Project;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by BarysevichD on 2017-06-15.
 */
public class ProjectControllerTest extends PopulateDBTest {
    @Test
    public void getById() throws Exception {
        List<Project> projects = (List) projectService.findByDescriptionContainingIgnoreCaseForTest(test);
        Project project = projects.get(0);

        ResponseEntity<String> response = restTemplate.getForEntity("/api/project/" + project.getId().toString(), String.class);

        JsonNode root = mapper.readTree(response.getBody());
        assertTrue(root.path("data").path("id").asText().equalsIgnoreCase(project.getId().toString()));
    }

    @Test
    public void save() throws Exception {
        String test = "Test4";

        List<Person> person = (List) personService.findByNameContainingIgnoreCaseForTest("test");
        Page<Position> positions = positionService.findByNameContainingIgnoreCase("test", new PageRequest(0, 10));


        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/project",
                        new Project(person.get(0).getId(), positions.getContent().get(0), test, test, test, null, null),
                        String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode description = root.path("data").path("description");
        assertEquals(description.asText(), test);
    }

    @Test
    public void update() throws Exception {
        String testUpdated = "TestUpdated";

        List<Project> projects = (List) projectService.findByDescriptionContainingIgnoreCaseForTest(test);
        Project project = projects.get(0);
        project.setDescription(testUpdated);

        HttpEntity<Project> requestEntity = new HttpEntity<>(project);

        ResponseEntity<String> response = restTemplate.exchange("/api/project/" + project.getId().toString(), HttpMethod.PUT, requestEntity, String.class);

        JsonNode root = mapper.readTree(response.getBody());
        JsonNode description = root.path("data").path("description");
        assertEquals(description.asText(), testUpdated);
    }

    @Test
    public void delete() throws Exception {
        List<Project> projects = (List) projectService.findByDescriptionContainingIgnoreCaseForTest(test);
        Project project = projects.get(0);

        HttpEntity<Project> requestEntity = new HttpEntity<>(new Project());

        ResponseEntity<String> response = restTemplate.exchange("/api/project/" + project.getId().toString(), HttpMethod.DELETE, requestEntity, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Project deletedProject = projectService.findOne(project.getId());

        assertEquals(deletedProject.isDeleted(), true);
    }

}