package com.barysevich.project.controller;

import com.barysevich.project.model.CompanyInfo;
import com.barysevich.project.model.Project;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by BarysevichD on 2017-06-15.
 */
public class CompanyInfoControllerTest extends PopulateDBTest {

    @Test
    public void getById() throws Exception {

        Page<CompanyInfo> companyInfos = companyInfoService.findByNameContainingIgnoreCase(test, new PageRequest(0, 10));
        CompanyInfo companyInfo = companyInfos.getContent().get(0);

        ResponseEntity<String> response = restTemplate.getForEntity("/api/company/" + companyInfo.getId().toString(), String.class);

        JsonNode root = mapper.readTree(response.getBody());
        assertTrue(root.path("data").path("id").asText().equalsIgnoreCase(companyInfo.getId().toString()));
    }

    @Test
    public void save() throws Exception {
        String test = "Test4";

        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/company", new CompanyInfo(test,
                        LocalDate.of(1970, Month.JANUARY, 1),
                        LocalDate.of(1970, Month.JANUARY, 1)), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("data").path("name");
        assertEquals(name.asText(), test);
    }

    @Test
    public void update() throws Exception {

        String testUpdated = "TestUpdated";

        Page<CompanyInfo> companyInfos = companyInfoService.findByNameContainingIgnoreCase(test, new PageRequest(0, 10));
        CompanyInfo companyInfo = companyInfos.getContent().get(0);
        companyInfo.setName(testUpdated);

        HttpEntity<CompanyInfo> requestEntity = new HttpEntity<>(companyInfo);

        ResponseEntity<String> response = restTemplate.exchange("/api/company/" + companyInfo.getId().toString(), HttpMethod.PUT, requestEntity, String.class);

        JsonNode root = mapper.readTree(response.getBody());
        JsonNode name = root.path("data").path("name");
        assertEquals(name.asText(), testUpdated);
    }

    @Test
    public void delete() throws Exception {

        Page<CompanyInfo> companyInfos = companyInfoService.findByNameContainingIgnoreCase(test, new PageRequest(0, 10));
        CompanyInfo companyInfo = companyInfos.getContent().get(0);

        HttpEntity<CompanyInfo> requestEntity = new HttpEntity<>(new CompanyInfo());

        ResponseEntity<String> response = restTemplate.exchange("/api/company/" + companyInfo.getId().toString(), HttpMethod.DELETE, requestEntity, String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        Iterable<Project> deletedProjects = projectService.findByCompanyId(companyInfo.getId());

        deletedProjects.forEach(project -> assertEquals(project.isDeleted(), true));

    }

}