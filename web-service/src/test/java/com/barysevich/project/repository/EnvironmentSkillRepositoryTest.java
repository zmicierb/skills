package com.barysevich.project.repository;

import com.barysevich.project.model.EnvironmentSkill;
import com.barysevich.project.model.Skill;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dima on 3/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EnvironmentSkillRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EnvironmentSkillRepository environmentSkillRepository;

    private Skill skill;

    private EnvironmentSkill environmentSkill1;
    private EnvironmentSkill environmentSkill2;
    private EnvironmentSkill environmentSkill3;

    @Before
    public void populateDB() {

        skill = entityManager.persist(new Skill("Test"));

        environmentSkill1 = environmentSkillRepository.save(new EnvironmentSkill(skill, 1));
        environmentSkill2 = environmentSkillRepository.save(new EnvironmentSkill(skill, 2));
        environmentSkill3 = environmentSkillRepository.save(new EnvironmentSkill(skill, 3));
    }

    @Test
    public void save() {

        String test = "Test";

        EnvironmentSkill environmentSkill = environmentSkillRepository.save(new EnvironmentSkill(skill, 1));

        assertThat(environmentSkill).hasFieldOrPropertyWithValue("skill.name", test);
    }

    @Test
    public void delete() {

        environmentSkillRepository.delete(environmentSkill1.getId());
        environmentSkillRepository.delete(environmentSkill2.getId());

        assertThat(environmentSkillRepository.findOne(environmentSkill1.getId())).isNull();
        assertThat(environmentSkillRepository.findOne(environmentSkill2.getId())).isNull();
    }

    @Test
    public void findAll() {

        Iterable<EnvironmentSkill> environmentRows = environmentSkillRepository.findAll();

        assertThat(environmentRows).contains(environmentSkill1, environmentSkill2, environmentSkill3);
    }

    @Test
    public void findOne() {

        EnvironmentSkill environmentSkill = environmentSkillRepository.findOne(environmentSkill2.getId());

        assertThat(environmentSkill).isNotEqualTo(environmentSkill1);
        assertThat(environmentSkill).isEqualTo(environmentSkill2);
    }

}