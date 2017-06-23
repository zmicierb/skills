package com.barysevich.project.repository;

import com.barysevich.project.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dima on 3/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SkillSumRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SkillSumRepository skillSumRepository;

    private Position position;
    private Department department;
    private Person person1;
    private Person person2;
    private Person person3;
    private Skill skill;
    private Row row;
    private SkillSum skillSum1;
    private SkillSum skillSum2;
    private SkillSum skillSum3;

    @Before
    public void populateDB() {

        position = entityManager.persist(new Position("test"));
        department = entityManager.persist(new Department("test"));
        person1 = entityManager.persist(new Person("test1", position, department, LocalDate.now()));
        person2 = entityManager.persist(new Person("test2", position, department, LocalDate.now()));
        person3 = entityManager.persist(new Person("test3", position, department, LocalDate.now()));
        skill = entityManager.persist(new Skill("test"));
        row = entityManager.persist(new Row("test"));

        skillSum1 = skillSumRepository.save(new SkillSum(person1.getId(), skill, row, 1));
        skillSum2 = skillSumRepository.save(new SkillSum(person2.getId(), skill, row, 2));
        skillSum3 = skillSumRepository.save(new SkillSum(person3.getId(), skill, row, 3));
    }

    @Test
    public void save() {

        SkillSum skillSum = skillSumRepository.save(new SkillSum(person1.getId(), skill, row, 1));

        assertThat(skillSum).hasFieldOrPropertyWithValue("personId", person1.getId());
    }

    @Test
    public void delete() {

        skillSumRepository.delete(skillSum1.getId());
        skillSumRepository.delete(skillSum2.getId());

        assertThat(skillSumRepository.findByPersonId(person1.getId()))
                .doesNotContain(skillSum1)
                .doesNotContain(skillSum2);
    }

    @Test
    public void findAll() {

        Iterable<SkillSum> skillSums = skillSumRepository.findAll();

        assertThat(skillSums).contains(skillSum1, skillSum2, skillSum3);
    }

    @Test
    public void findOne() {

        SkillSum skillSum = skillSumRepository.findOne(skillSum2.getId());

        assertThat(skillSum).isNotEqualTo(skillSum1);
        assertThat(skillSum).isEqualTo(skillSum2);
    }

    @Test
    public void findByPersonId() {

        Iterable<SkillSum> skillSums = skillSumRepository.findByPersonId(person2.getId());

        assertThat(skillSums).contains(skillSum2);
        assertThat(skillSums).doesNotContain(skillSum1);
    }

    @Test
    public void deleteByPersonId() {

        skillSumRepository.deleteByPersonId(person2.getId());
        Iterable<SkillSum> skillSums1 = skillSumRepository.findByPersonId(person1.getId());
        Iterable<SkillSum> skillSums2 = skillSumRepository.findByPersonId(person2.getId());

        assertThat(skillSums1).contains(skillSum1);
        assertThat(skillSums2).doesNotContain(skillSum2);
    }

}