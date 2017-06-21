package com.barysevich.project.service;

import com.barysevich.project.model.EnvironmentSkill;
import com.barysevich.project.model.Position;
import com.barysevich.project.model.Project;
import com.barysevich.project.model.Skill;
import com.barysevich.project.repository.EnvironmentSkillRepository;
import com.barysevich.project.repository.PositionRepository;
import com.barysevich.project.repository.ProjectRepository;
import com.barysevich.project.repository.SkillRepository;
import com.barysevich.project.service.impl.ProjectServiceImpl;
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

import static org.mockito.Mockito.*;

/**
 * Created by BarysevichD on 2017-06-21.
 */
@RunWith(SpringRunner.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private PositionRepository positionRepository;

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private EnvironmentSkillRepository environmentSkillRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Long projectId;
    private Long personId;
    private String name;
    private Position position;
    private Skill skill1;
    private Skill skill2;
    private Skill skill3;
    private EnvironmentSkill environmentSkill1;
    private EnvironmentSkill environmentSkill2;
    private List<EnvironmentSkill> environmentSkills;
    private Project project;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Before
    public void createModels() {
        projectId = 1L;
        personId = 1L;
        name = "test";

        skill1 = new Skill("skill1");
        skill2 = new Skill("skill2");
        skill3 = new Skill("skill3");
        position = new Position(name);

        environmentSkill1 = new EnvironmentSkill(skill1, 1);
        environmentSkill2 = new EnvironmentSkill(skill2, 2);
        environmentSkills = new ArrayList<>(Arrays.asList(environmentSkill1, environmentSkill2));

        project = new Project(personId, position, "test", "test", "test", environmentSkills, null);
        project.setId(projectId);
    }

    @Test
    public void remove() throws Exception {
        projectService.remove(projectId);

        verify(projectRepository, times(1)).remove(projectId);
    }

    @Test
    public void updateAddNewOne() throws Exception {

        when(projectRepository.findOne(projectId)).thenReturn(project);
        when(positionRepository.findByName(name)).thenReturn(position);
        when(skillRepository.findByName("skill1")).thenReturn(skill1);
        when(skillRepository.findByName("skill2")).thenReturn(skill2);
        when(skillRepository.findByName("skill3")).thenReturn(skill3);

        EnvironmentSkill environmentSkillNew1 = new EnvironmentSkill(skill1, 1);
        EnvironmentSkill environmentSkillNew2 = new EnvironmentSkill(skill2, 2);
        EnvironmentSkill environmentSkillNew3 = new EnvironmentSkill(skill3, 3);
        List<EnvironmentSkill> environmentSkillsNew = new ArrayList<>(Arrays.asList(environmentSkillNew1, environmentSkillNew2, environmentSkillNew3));

        Project projectNew = new Project(personId, position, "test", "test", "test", environmentSkillsNew, null);

        projectService.update(projectId, projectNew);

        verify(projectRepository, times(1)).findOne(projectId);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void updateRemoveOne() throws Exception {

        when(projectRepository.findOne(projectId)).thenReturn(project);
        when(positionRepository.findByName(name)).thenReturn(position);
        when(skillRepository.findByName("skill1")).thenReturn(skill1);
        when(skillRepository.findByName("skill2")).thenReturn(skill2);
        when(skillRepository.findByName("skill3")).thenReturn(skill3);

        List<EnvironmentSkill> environmentSkillsNew = environmentSkills.subList(0, 1);
        Project projectNew = new Project(personId, position, "test", "test", "test", environmentSkillsNew, null);

        projectService.update(personId, projectNew);

        verify(environmentSkillRepository, times(1)).delete(environmentSkill2);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void updateChangeOne() throws Exception {

        when(projectRepository.findOne(projectId)).thenReturn(project);
        when(positionRepository.findByName(name)).thenReturn(position);
        when(skillRepository.findByName("skill1")).thenReturn(skill1);
        when(skillRepository.findByName("skill2")).thenReturn(skill2);
        when(skillRepository.findByName("skill3")).thenReturn(skill3);

        EnvironmentSkill environmentSkillNew = new EnvironmentSkill(skill1, 10);
        List<EnvironmentSkill> environmentSkillsNew = new ArrayList<>(Arrays.asList(environmentSkillNew, environmentSkill2));

        Project projectNew = new Project(personId, position, "test", "test", "test", environmentSkillsNew, null);

        projectService.update(projectId, projectNew);

        verify(projectRepository, times(1)).findOne(projectId);
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void findByPersonId() throws Exception {

        projectService.findByPersonId(personId);

        verify(projectRepository, times(1)).findByPersonId(personId);
    }

    @Test
    public void save() throws Exception {
        when(positionRepository.findByName(name)).thenReturn(position);

        projectService.save(project);

        verify(projectRepository, times(1)).save(project);
    }

    @Test
    public void findByCompanyId() throws Exception {
        Long companyId = 1L;

        projectService.findByCompanyId(companyId);

        verify(projectRepository, times(1)).findByCompanyId(companyId);
    }

}