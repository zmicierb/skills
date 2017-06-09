package com.barysevich.project.repository;

import com.barysevich.project.model.Row;
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
public class RowRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RowRepository rowRepository;

    @Test
    public void save() {
        Row row = rowRepository.save(new Row("Test"));

        assertThat(row).hasFieldOrPropertyWithValue("name", "Test");
    }

    @Test
    public void delete() {

        Row row1 = entityManager.persist(new Row("Test1"));
        Row row2 = entityManager.persist(new Row("Test2"));

        rowRepository.delete(row1.getId());
        rowRepository.delete(row2.getId());

        assertThat(rowRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20)))
                .doesNotContain(row1)
                .doesNotContain(row2);
    }

    @Test
    public void findAll() {

        Row row1 = entityManager.persist(new Row("Test1"));
        Row row2 = entityManager.persist(new Row("Test2"));
        Row row3 = entityManager.persist(new Row("Test3"));


        Iterable<Row> rows = rowRepository.findAll();

        assertThat(rows).contains(row1, row2, row3);
    }

    @Test
    public void findOne() {

        Row row1 = entityManager.persist(new Row("Test1"));
        Row row2 = entityManager.persist(new Row("Test2"));

        Row row = rowRepository.findOne(row2.getId());

        assertThat(row).isNotEqualTo(row1);
        assertThat(row).isEqualTo(row2);
    }

    @Test
    public void findByName() {

        Row row1 = entityManager.persist(new Row("Test1"));
        Row row2 = entityManager.persist(new Row("Test2"));

        Iterable<Row> rows = rowRepository.findByNameContainingIgnoreCase("ST1", new PageRequest(0, 20));

        assertThat(rows).contains(row1);
        assertThat(rows).doesNotContain(row2);
    }

}