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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dima on 3/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SkillSumRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SkillSumRepository skillSumRepository;

    @Test
    public void save() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Skill skill = entityManager.persist(new Skill("test"));
        Row row = entityManager.persist(new Row("test"));

        SkillSum skillSum = skillSumRepository.save(new SkillSum(person, skill, row, 1));

        assertThat(skillSum).hasFieldOrPropertyWithValue("person", person);
    }

    @Test
    public void delete() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Skill skill = entityManager.persist(new Skill("test"));
        Row row = entityManager.persist(new Row("test"));

        SkillSum skillSum1 = skillSumRepository.save(new SkillSum(person, skill, row, 1));
        SkillSum skillSum2 = skillSumRepository.save(new SkillSum(person, skill, row, 2));

        skillSumRepository.delete(skillSum1.getId());
        skillSumRepository.delete(skillSum2.getId());

        assertThat(skillSumRepository.findByPersonId(person.getId()))
                .doesNotContain(skillSum1)
                .doesNotContain(skillSum2);
    }

    @Test
    public void findAll() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Skill skill = entityManager.persist(new Skill("test"));
        Row row = entityManager.persist(new Row("test"));

        SkillSum skillSum1 = skillSumRepository.save(new SkillSum(person, skill, row, 1));
        SkillSum skillSum2 = skillSumRepository.save(new SkillSum(person, skill, row, 2));
        SkillSum skillSum3 = skillSumRepository.save(new SkillSum(person, skill, row, 3));

        Iterable<SkillSum> skillSums = skillSumRepository.findAll();

        assertThat(skillSums).contains(skillSum1, skillSum2, skillSum3);
    }

    @Test
    public void findOne() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person = entityManager.persist(new Person("test", position, department, LocalDate.now()));
        Skill skill = entityManager.persist(new Skill("test"));
        Row row = entityManager.persist(new Row("test"));

        SkillSum skillSum1 = skillSumRepository.save(new SkillSum(person, skill, row, 1));
        SkillSum skillSum2 = skillSumRepository.save(new SkillSum(person, skill, row, 2));

        SkillSum skillSum = skillSumRepository.findOne(skillSum2.getId());

        assertThat(skillSum).isNotEqualTo(skillSum1);
        assertThat(skillSum).isEqualTo(skillSum2);
    }

    @Test
    public void findByPersonId() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person1 = entityManager.persist(new Person("test1", position, department, LocalDate.now()));
        Person person2 = entityManager.persist(new Person("test2", position, department, LocalDate.now()));
        Skill skill = entityManager.persist(new Skill("test"));
        Row row = entityManager.persist(new Row("test"));

        SkillSum skillSum1 = skillSumRepository.save(new SkillSum(person1, skill, row, 1));
        SkillSum skillSum2 = skillSumRepository.save(new SkillSum(person2, skill, row, 2));

        Iterable<SkillSum> skillSums = skillSumRepository.findByPersonId(person2.getId());

        assertThat(skillSums).contains(skillSum2);
        assertThat(skillSums).doesNotContain(skillSum1);
    }

    @Test
    public void deleteByPersonId() {

        Position position = entityManager.persist(new Position("test"));
        Department department = entityManager.persist(new Department("test"));
        Person person1 = entityManager.persist(new Person("test1", position, department, LocalDate.now()));
        Person person2 = entityManager.persist(new Person("test2", position, department, LocalDate.now()));
        Skill skill = entityManager.persist(new Skill("test"));
        Row row = entityManager.persist(new Row("test"));

        SkillSum skillSum1 = skillSumRepository.save(new SkillSum(person1, skill, row, 1));
        SkillSum skillSum2 = skillSumRepository.save(new SkillSum(person2, skill, row, 2));

        skillSumRepository.deleteByPersonId(person2.getId());
        Iterable<SkillSum> skillSums1 = skillSumRepository.findByPersonId(person1.getId());
        Iterable<SkillSum> skillSums2 = skillSumRepository.findByPersonId(person2.getId());

        assertThat(skillSums1).contains(skillSum1);
        assertThat(skillSums2).doesNotContain(skillSum2);
    }

}