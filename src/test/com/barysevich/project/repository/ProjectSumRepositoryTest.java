package com.barysevich.project.repository;

import com.barysevich.project.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dima on 3/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProjectSumRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectSumRepository projectSumRepository;

    @Test
    public void save() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Project project = entityManager.persist(new Project());
        CompanyInfo companyInfo = entityManager.persist(new CompanyInfo("test",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));

        ProjectSum projectSum = projectSumRepository.save(new ProjectSum(person, project, companyInfo));

        assertThat(projectSum).hasFieldOrPropertyWithValue("companyInfo", companyInfo);
    }

    @Test
    public void delete() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Project project = entityManager.persist(new Project());
        CompanyInfo companyInfo = entityManager.persist(new CompanyInfo("test",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));

        ProjectSum projectSum1 = projectSumRepository.save(new ProjectSum(person, project, companyInfo));
        ProjectSum projectSum2 = projectSumRepository.save(new ProjectSum(person, project, companyInfo));

        projectSumRepository.delete(projectSum1.getId());
        projectSumRepository.delete(projectSum2.getId());

        assertThat(projectSumRepository.findByPersonId(person.getId()))
                .doesNotContain(projectSum1)
                .doesNotContain(projectSum2);
    }

    @Test
    public void findAll() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Project project = entityManager.persist(new Project());
        CompanyInfo companyInfo = entityManager.persist(new CompanyInfo("test",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));

        ProjectSum projectSum1 = projectSumRepository.save(new ProjectSum(person, project, companyInfo));
        ProjectSum projectSum2 = projectSumRepository.save(new ProjectSum(person, project, companyInfo));
        ProjectSum projectSum3 = projectSumRepository.save(new ProjectSum(person, project, companyInfo));

        Iterable<ProjectSum> projectSums = projectSumRepository.findAll();

        assertThat(projectSums).contains(projectSum1, projectSum2, projectSum3);
    }

    @Test
    public void findOne() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Project project = entityManager.persist(new Project());
        CompanyInfo companyInfo = entityManager.persist(new CompanyInfo("test",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));

        ProjectSum projectSum1 = projectSumRepository.save(new ProjectSum(person, project, companyInfo));
        ProjectSum projectSum2 = projectSumRepository.save(new ProjectSum(person, project, companyInfo));

        ProjectSum projectSum = projectSumRepository.findOne(projectSum2.getId());

        assertThat(projectSum).isNotEqualTo(projectSum1);
        assertThat(projectSum).isEqualTo(projectSum2);
    }

    @Test
    public void findByPersonId() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person1 = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Person person2 = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Project project = entityManager.persist(new Project());
        CompanyInfo companyInfo = entityManager.persist(new CompanyInfo("test",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));

        ProjectSum projectSum1 = projectSumRepository.save(new ProjectSum(person1, project, companyInfo));
        ProjectSum projectSum2 = projectSumRepository.save(new ProjectSum(person2, project, companyInfo));

        Iterable<ProjectSum> projectSums = projectSumRepository.findByPersonId(person2.getId());

        assertThat(projectSums).contains(projectSum2);
        assertThat(projectSums).doesNotContain(projectSum1);
    }

}