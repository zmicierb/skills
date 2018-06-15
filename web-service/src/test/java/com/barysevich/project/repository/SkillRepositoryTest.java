package com.barysevich.project.repository;

import com.barysevich.project.model.Skill;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SkillRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SkillRepository skillRepository;

    private Skill skill1;
    private Skill skill2;
    private Skill skill3;

    @Before
    public void populateDB() {
        skill1 = entityManager.persist(new Skill("Test1"));
        skill2 = entityManager.persist(new Skill("Test2"));
        skill3 = entityManager.persist(new Skill("Test3"));
    }

    @Test
    public void save() {
        Skill skill = skillRepository.save(new Skill("Test"));

        assertThat(skill).hasFieldOrPropertyWithValue("name", "Test");
    }

    @Test
    public void delete() {

        skillRepository.delete(skill1.getId());
        skillRepository.delete(skill2.getId());

        assertThat(skillRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20)))
                .doesNotContain(skill1)
                .doesNotContain(skill2);
    }

    @Test
    public void findAll() {

        Iterable<Skill> skills = skillRepository.findAll();

        assertThat(skills).contains(skill1, skill2, skill3);
    }

    @Test
    public void findOne() {

        Skill skill = skillRepository.findOne(skill2.getId());

        assertThat(skill).isNotEqualTo(skill1);
        assertThat(skill).isEqualTo(skill2);
    }

    @Test
    public void findByNameContainingIgnoreCase() {

        Iterable<Skill> skills = skillRepository.findByNameContainingIgnoreCase("ST1", new PageRequest(0, 20));

        assertThat(skills).contains(skill1);
        assertThat(skills).doesNotContain(skill2);
    }

    @Test
    public void findByName() {

        Skill skill = skillRepository.findByName("Test1");

        assertThat(skill).isEqualTo(skill1);
        assertThat(skill).isNotEqualTo(skill2);
    }

}