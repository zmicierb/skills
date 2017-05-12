package com.barysevich.project.repository;

import com.barysevich.project.model.Position;
import com.barysevich.project.model.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dima on 3/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void save() {

        Position position = entityManager.persist(new Position("test"));

        Project project = projectRepository.save(new Project(position, "test", "test", "test"));

        assertThat(project).hasFieldOrPropertyWithValue("result", "test");
    }

    @Test
    public void delete() {

        Position position = entityManager.persist(new Position("test"));

        Project project1 = projectRepository.save(new Project(position, "test1", "test1", "test1"));
        Project project2 = projectRepository.save(new Project(position, "test2", "test2", "test2"));

        projectRepository.remove(project1.getId());
        projectRepository.remove(project2.getId());

        assertThat(projectRepository.findByResponsibilityContainingIgnoreCase("test", new PageRequest(0, 20)))
                .doesNotContain(project1)
                .doesNotContain(project2);
    }

    @Test
    public void findAll() {

        Position position = entityManager.persist(new Position("test"));

        Project project1 = projectRepository.save(new Project(position, "test1", "test1", "test1"));
        Project project2 = projectRepository.save(new Project(position, "test2", "test2", "test2"));
        Project project3 = projectRepository.save(new Project(position, "test3", "test3", "test3"));


        Iterable<Project> projects = projectRepository.findAll();

        assertThat(projects).contains(project1, project2, project3);
    }

    @Test
    public void findOne() {

        Position position = entityManager.persist(new Position("test"));

        Project project1 = projectRepository.save(new Project(position, "test1", "test1", "test1"));
        Project project2 = projectRepository.save(new Project(position, "test2", "test2", "test2"));

        Project project = projectRepository.findOne(project2.getId());

        assertThat(project).isEqualTo(project2);
    }

}