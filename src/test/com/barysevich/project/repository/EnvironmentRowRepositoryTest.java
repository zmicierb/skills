package com.barysevich.project.repository;

import com.barysevich.project.model.EnvironmentRow;
import com.barysevich.project.model.Skill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dima on 3/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EnvironmentRowRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EnvironmentRowRepository environmentRowRepository;

    @Test
    public void save() {

        Skill skill = entityManager.persist(new Skill("Test"));

        EnvironmentRow environmentRow = environmentRowRepository.save(new EnvironmentRow(1L, skill, 1));

        assertThat(environmentRow).hasFieldOrPropertyWithValue("projectId", 1L);
    }

    @Test
    public void delete() {

        Skill skill = entityManager.persist(new Skill("Test"));

        EnvironmentRow environmentRow1 = environmentRowRepository.save(new EnvironmentRow(1l, skill, 1));
        EnvironmentRow environmentRow2 = environmentRowRepository.save(new EnvironmentRow(2l, skill, 2));

        environmentRowRepository.delete(environmentRow1.getId());
        environmentRowRepository.delete(environmentRow2.getId());

        assertThat(environmentRowRepository.findOne(environmentRow1.getId())).isNull();
        assertThat(environmentRowRepository.findOne(environmentRow2.getId())).isNull();
    }

    @Test
    public void findAll() {

        Skill skill = entityManager.persist(new Skill("Test"));

        EnvironmentRow environmentRow1 = environmentRowRepository.save(new EnvironmentRow(1l, skill, 1));
        EnvironmentRow environmentRow2 = environmentRowRepository.save(new EnvironmentRow(2l, skill, 2));
        EnvironmentRow environmentRow3 = environmentRowRepository.save(new EnvironmentRow(3l, skill, 3));


        Iterable<EnvironmentRow> environmentRows = environmentRowRepository.findAll();

        assertThat(environmentRows).contains(environmentRow1, environmentRow2, environmentRow3);
    }

    @Test
    public void findOne() {

        Skill skill = entityManager.persist(new Skill("Test"));

        EnvironmentRow environmentRow1 = environmentRowRepository.save(new EnvironmentRow(1l, skill, 1));
        EnvironmentRow environmentRow2 = environmentRowRepository.save(new EnvironmentRow(2l, skill, 2));

        EnvironmentRow environmentRow = environmentRowRepository.findOne(environmentRow2.getId());

        assertThat(environmentRow).isEqualTo(environmentRow2);
    }

}