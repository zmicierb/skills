package com.barysevich.project.repository;

import com.barysevich.project.model.Department;
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
public class DepartmentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void save() {
        Department department = departmentRepository.save(new Department("Test"));

        assertThat(department).hasFieldOrPropertyWithValue("name", "Test");
    }

    @Test
    public void delete() {

        Department department1 = entityManager.persist(new Department("Test1"));
        Department department2 = entityManager.persist(new Department("Test2"));

        departmentRepository.delete(department1.getId());
        departmentRepository.delete(department2.getId());

        assertThat(departmentRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20)))
                .doesNotContain(department1)
                .doesNotContain(department2);
    }

    @Test
    public void findAll() {

        Department department1 = entityManager.persist(new Department("Test1"));
        Department department2 = entityManager.persist(new Department("Test2"));
        Department department3 = entityManager.persist(new Department("Test3"));


        Iterable<Department> departments = departmentRepository.findAll();

        assertThat(departments).contains(department1, department2, department3);
    }

    @Test
    public void findOne() {

        Department department1 = entityManager.persist(new Department("Test1"));
        Department department2 = entityManager.persist(new Department("Test2"));

        Department department = departmentRepository.findOne(department1.getId());

        assertThat(department).isEqualTo(department1);
        assertThat(department).isNotEqualTo(department2);
    }

    @Test
    public void findByNameContainingIgnoreCase() {

        Department department1 = entityManager.persist(new Department("Test1"));
        Department department2 = entityManager.persist(new Department("Test2"));

        Iterable<Department> departments = departmentRepository.findByNameContainingIgnoreCase("ST1", new PageRequest(0, 20));

        assertThat(departments).contains(department1);
        assertThat(departments).doesNotContain(department2);
    }

    @Test
    public void findByName() {

        Department department1 = entityManager.persist(new Department("Test1"));
        Department department2 = entityManager.persist(new Department("Test11"));

        Department department = departmentRepository.findByName("Test1");

        assertThat(department).isEqualTo(department1);
        assertThat(department).isNotEqualTo(department2);
    }

}