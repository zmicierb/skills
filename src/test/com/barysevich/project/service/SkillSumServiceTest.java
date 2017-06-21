package com.barysevich.project.service;

import com.barysevich.project.model.Row;
import com.barysevich.project.model.Skill;
import com.barysevich.project.model.SkillSum;
import com.barysevich.project.repository.SkillRepository;
import com.barysevich.project.repository.SkillSumRepository;
import com.barysevich.project.service.impl.SkillSumServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by BarysevichD on 2017-06-20.
 */
@RunWith(SpringRunner.class)
public class SkillSumServiceTest {

    @Mock
    private SkillSumRepository skillSumRepository;

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillSumServiceImpl skillSumService;

    private List<SkillSum> skillSums;
    private SkillSum skillSum1;
    private SkillSum skillSum2;
    private Long personId;
    private Skill skill1;
    private Skill skill2;
    private Row row;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void createModels() {
        personId = 1L;
        skill1 = new Skill("test1");
        skill2 = new Skill("test2");

        row = new Row("test");
        skillSum1 = new SkillSum(personId, skill1, row, 1);
        skillSum1.setId(1L);
        skillSum2 = new SkillSum(personId, skill2, row, 2);
        skillSum2.setId(2L);
        skillSums = new ArrayList<>(Arrays.asList(skillSum1, skillSum2));
    }

    @Test
    public void findByPersonId() {

        when(skillSumRepository.findByPersonId(personId)).thenReturn(skillSums);

        List<SkillSum> result = (List) skillSumService.findByPersonId(personId);

        assertEquals(skillSums.size(), result.size());
        verify(skillSumRepository, times(1)).findByPersonId(personId);
    }

    @Test
    public void updateAddNew() throws Exception {

        Skill skill3 = new Skill("test3");
        Skill skill4 = new Skill("test4");

        when(skillSumRepository.findByPersonId(personId)).thenReturn(skillSums);
        when(skillRepository.findByName(skill3.getName())).thenReturn(skill3);

        SkillSum skillSumNew1 = new SkillSum(personId, skill1, row, 1);
        SkillSum skillSumNew2 = new SkillSum(personId, skill2, row, 2);
        //saved skill
        SkillSum skillSumNew3 = new SkillSum(personId, skill3, row, 3);
        //non-saved skill
        SkillSum skillSumNew4 = new SkillSum(personId, skill4, row, 4);
        List<SkillSum> skillSumsNew = new ArrayList<>(Arrays.asList(skillSumNew1, skillSumNew2, skillSumNew3, skillSumNew4));

        skillSumService.update(personId, skillSumsNew);

        verify(skillSumRepository, times(2)).save(skillSumNew3);
        verify(skillRepository, times(1)).save(skill4);
    }

    @Test
    public void updateRemoveOne() throws Exception {

        when(skillSumRepository.findByPersonId(personId)).thenReturn(skillSums);

        List<SkillSum> skillSumsNew = skillSums.subList(0, 1);

        skillSumService.update(personId, skillSumsNew);

        verify(skillSumRepository, times(1)).delete(skillSums.get(1).getId());

    }

    @Test
    public void updateChangeOne() throws Exception {

        when(skillSumRepository.findByPersonId(personId)).thenReturn(skillSums);

        SkillSum skillSumNew = new SkillSum(personId, skill1, row, 10);
        List<SkillSum> skillSumsNew = new ArrayList<>(Arrays.asList(skillSumNew, skillSum2));

        skillSumService.update(personId, skillSumsNew);

        verify(skillSumRepository, times(1)).save(skillSums.get(0));
    }

}