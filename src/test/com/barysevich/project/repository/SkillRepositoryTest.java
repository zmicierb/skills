package com.barysevich.project.repository;

import com.barysevich.project.model.Skill;
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
public class SkillRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SkillRepository skillRepository;

    @Test
    public void save() {
        Skill skill = skillRepository.save(new Skill("Test"));

        assertThat(skill).hasFieldOrPropertyWithValue("name", "Test");
    }

    @Test
    public void delete() {

        Skill skill1 = entityManager.persist(new Skill("Test1"));
        Skill skill2 = entityManager.persist(new Skill("Test2"));

        skillRepository.delete(skill1.getId());
        skillRepository.delete(skill2.getId());

        assertThat(skillRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20)))
                .doesNotContain(skill1)
                .doesNotContain(skill2);
    }

    @Test
    public void findAll() {

        Skill skill1 = entityManager.persist(new Skill("Test1"));
        Skill skill2 = entityManager.persist(new Skill("Test2"));
        Skill skill3 = entityManager.persist(new Skill("Test3"));


        Iterable<Skill> skills = skillRepository.findAll();

        assertThat(skills).contains(skill1, skill2, skill3);
    }

    @Test
    public void findOne() {

        Skill skill1 = entityManager.persist(new Skill("Test1"));
        Skill skill2 = entityManager.persist(new Skill("Test2"));

        Skill skill = skillRepository.findOne(skill2.getId());

        assertThat(skill).isNotEqualTo(skill1);
        assertThat(skill).isEqualTo(skill2);
    }

    @Test
    public void findByNameContainingIgnoreCase() {

        Skill skill1 = entityManager.persist(new Skill("Test1"));
        Skill skill2 = entityManager.persist(new Skill("Test2"));

        Iterable<Skill> skills = skillRepository.findByNameContainingIgnoreCase("ST1", new PageRequest(0, 20));

        assertThat(skills).contains(skill1);
        assertThat(skills).doesNotContain(skill2);
    }

    @Test
    public void findByName() {

        Skill skill1 = entityManager.persist(new Skill("Test1"));
        Skill skill2 = entityManager.persist(new Skill("Test11"));

        Skill skill = skillRepository.findByName("Test1");

        assertThat(skill).isEqualTo(skill1);
        assertThat(skill).isNotEqualTo(skill2);
    }

}