package com.barysevich.project.service;

import com.barysevich.project.model.*;
import com.barysevich.project.repository.DepartmentRepository;
import com.barysevich.project.repository.PersonRepository;
import com.barysevich.project.repository.PositionRepository;
import com.barysevich.project.repository.SkillSumRepository;
import com.barysevich.project.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by BarysevichD on 2017-06-21.
 */
@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private PositionService positionService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private SkillSumRepository skillSumRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void remove() throws Exception {
        Long personId = 1L;

        personService.remove(personId);

        verify(personRepository, times(1)).remove(personId);
    }

    @Test
    public void findByNameContainingIgnoreCase() throws Exception {
        String test = "test";

        personService.findByNameContainingIgnoreCase(test, new PageRequest(0, 10));

        verify(personRepository, times(1)).findByNameContainingIgnoreCase(test, new PageRequest(0, 10));
    }

    @Test
    public void update() throws Exception {
        String name = "test";
        String email = "test@test.com";
        Long personId = 1L;
        Position position = new Position(name);
        Department department = new Department(name);
        Person person = new Person(name, email, position, department, LocalDate.now());
        person.setId(personId);

        when(personRepository.findOne(personId)).thenReturn(person);
        when(positionRepository.findByName(name)).thenReturn(position);
        when(departmentRepository.findByName(name)).thenReturn(department);
        when(departmentService.save(department)).thenReturn(department);
        when(positionService.save(position)).thenReturn(position);

        personService.update(personId, person);

        verify(personRepository, times(1)).findOne(personId);
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void findSkillsById() throws Exception {
        Long personId = 1L;
        Skill skill1 = new Skill("test1");
        Skill skill2 = new Skill("test2");

        Row row = new Row("test");
        SkillSum skillSum1 = new SkillSum(personId, skill1, row, 1);
        skillSum1.setId(1L);
        SkillSum skillSum2 = new SkillSum(personId, skill2, row, 2);
        skillSum2.setId(2L);

        List<SkillSum> skillSums = new ArrayList<>(Arrays.asList(skillSum1, skillSum2));

        when(skillSumRepository.findByPersonId(personId)).thenReturn(skillSums);

        personService.findSkillsById(personId);

        verify(skillSumRepository, times(1)).findByPersonId(personId);
    }

}