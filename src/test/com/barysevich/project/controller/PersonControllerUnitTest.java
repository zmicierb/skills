package com.barysevich.project.controller;

import com.barysevich.project.service.PersonService;
import com.barysevich.project.service.ProjectService;
import com.barysevich.project.service.SkillService;
import com.barysevich.project.service.SkillSumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dima on 3/26/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@EnableSpringDataWebSupport
public class PersonControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personServiceMock;

    @MockBean
    private ProjectService projectServiceMock;

    @MockBean
    private SkillService skillServiceMock;

    @MockBean
    private SkillSumService skillSumServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void savePerson() throws Exception {
        mockMvc.perform(get("/api/person/find/Di").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}