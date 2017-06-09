package com.barysevich.project.repository;

import com.barysevich.project.model.Position;
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
public class PositionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PositionRepository positionRepository;

    @Test
    public void save() {
        Position position = positionRepository.save(new Position("Test"));

        assertThat(position).hasFieldOrPropertyWithValue("name", "Test");
    }

    @Test
    public void delete() {

        Position position1 = entityManager.persist(new Position("Test1"));
        Position position2 = entityManager.persist(new Position("Test2"));

        positionRepository.delete(position1.getId());
        positionRepository.delete(position2.getId());

        assertThat(positionRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20)))
                .doesNotContain(position1)
                .doesNotContain(position2);
    }

    @Test
    public void findAll() {

        Position position1 = entityManager.persist(new Position("Test1"));
        Position position2 = entityManager.persist(new Position("Test2"));
        Position position3 = entityManager.persist(new Position("Test3"));


        Iterable<Position> positions = positionRepository.findAll();

        assertThat(positions).contains(position1, position2, position3);
    }

    @Test
    public void findOne() {

        Position position1 = entityManager.persist(new Position("Test1"));
        Position position2 = entityManager.persist(new Position("Test2"));

        Position position = positionRepository.findOne(position2.getId());

        assertThat(position).isNotEqualTo(position1);
        assertThat(position).isEqualTo(position2);
    }

    @Test
    public void findByNameContainingIgnoreCase() {

        Position position1 = entityManager.persist(new Position("Test1"));
        Position position2 = entityManager.persist(new Position("Test2"));

        Iterable<Position> positions = positionRepository.findByNameContainingIgnoreCase("ST1", new PageRequest(0, 20));

        assertThat(positions).contains(position1);
        assertThat(positions).doesNotContain(position2);
    }

    @Test
    public void findByName() {

        Position position1 = entityManager.persist(new Position("Test1"));
        Position position2 = entityManager.persist(new Position("Test11"));

        Position position = positionRepository.findByName("Test1");

        assertThat(position).isEqualTo(position1);
        assertThat(position).isNotEqualTo(position2);
    }

}