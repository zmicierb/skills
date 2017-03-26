package com.barysevich.project.controller;

import com.barysevich.project.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepositoryMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void savePerson() throws Exception {
        mockMvc.perform(get("/api/person/find/name=Di").accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}