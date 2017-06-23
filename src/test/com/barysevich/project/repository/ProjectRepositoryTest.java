package com.barysevich.project.repository;

import com.barysevich.project.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dima on 3/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepository;

    private Position position;

    private Department department;

    private Skill skill;

    private Person person;

    private CompanyInfo companyInfo;

    private EnvironmentSkill environmentSkill;

    private Project project1;
    private Project project2;
    private Project project3;

    @Before
    public void populateDB() {
        position = entityManager.persist(new Position("test"));
        department = entityManager.persist(new Department("test"));
        skill = entityManager.persist(new Skill("test"));

        person = entityManager.persist(new Person("Test",
                position,
                department,
                LocalDate.of(1970, Month.JANUARY, 1)));
        companyInfo = entityManager.persist(new CompanyInfo("Test", LocalDate.now(), LocalDate.now()));

        environmentSkill = entityManager.persist(new EnvironmentSkill(skill, 1));
        List<EnvironmentSkill> environmentSkills = new ArrayList<>();
        environmentSkills.add(environmentSkill);

        project1 = entityManager.persist(new Project(person.getId(), position, "test1", "test1", "test1", environmentSkills, companyInfo));
        project2 = entityManager.persist(new Project(person.getId(), position, "test2", "test2", "test2", environmentSkills, companyInfo));
        project3 = entityManager.persist(new Project(person.getId(), position, "test2", "test2", "test2", environmentSkills, companyInfo));
    }

    @Test
    public void save() {

        List<EnvironmentSkill> environmentSkills = new ArrayList<EnvironmentSkill>();
        environmentSkills.add(entityManager.persist(new EnvironmentSkill(skill, 1)));

        Project project = projectRepository.save(new Project(person.getId(), position, "test", "test", "test", environmentSkills, companyInfo));

        assertThat(project).hasFieldOrPropertyWithValue("result", "test");
    }

    @Test
    public void remove() {

        projectRepository.remove(project1.getId());
        projectRepository.remove(project2.getId());

        assertThat(projectRepository.findByResponsibilityContainingIgnoreCase("test", new PageRequest(0, 20)))
                .doesNotContain(project1)
                .doesNotContain(project2);
    }

    @Test
    public void findAll() {

        Iterable<Project> projects = projectRepository.findAll();

        assertThat(projects).contains(project1, project2, project3);
    }

    @Test
    public void findOne() {

        Project project = projectRepository.findOne(project2.getId());

        assertThat(project).isNotEqualTo(project1);
        assertThat(project).isEqualTo(project2);
    }

    @Test
    public void findByResponsibilityContainingIgnoreCase() {

        Iterable<Project> projects = projectRepository.findByResponsibilityContainingIgnoreCase("TEST1", new PageRequest(0, 20));

        assertThat(projects).contains(project1);
        assertThat(projects).doesNotContain(project2, project3);
    }

    @Test
    public void findByPersonId() {

        Iterable<Project> projects = projectRepository.findByPersonId(person.getId());

        assertThat(projects).contains(project1, project2, project3);
    }

    @Test
    public void findByCompanyId() {

        Iterable<Project> projects = projectRepository.findByCompanyId(companyInfo.getId());

        assertThat(projects).contains(project1, project2, project3);
    }

    @Test
    public void findByDescriptionContainingIgnoreCaseForTest() {

        Iterable<Project> projects = projectRepository.findByDescriptionContainingIgnoreCaseForTest("TEST1");

        assertThat(projects).contains(project1);
        assertThat(projects).doesNotContain(project2, project3);
    }
}