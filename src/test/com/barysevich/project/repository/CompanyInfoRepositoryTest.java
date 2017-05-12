package com.barysevich.project.repository;

import com.barysevich.project.model.CompanyInfo;
import com.barysevich.project.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by dima on 3/25/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyInfoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Test
    public void save() {
        CompanyInfo customer = companyInfoRepository.save(new CompanyInfo("Test",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                new Position("test")));

        assertThat(customer).hasFieldOrPropertyWithValue("name", "Test");
    }

    @Test
    public void delete() {

        Position position = entityManager.persist(new Position("test"));

        CompanyInfo companyInfo1 = entityManager.persist(new CompanyInfo("Test1",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));
        CompanyInfo companyInfo2 = entityManager.persist(new CompanyInfo("Test2",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));

        companyInfoRepository.remove(companyInfo1.getId());
        companyInfoRepository.remove(companyInfo2.getId());

        assertThat(companyInfoRepository.findByNameContainingIgnoreCase("test", new PageRequest(0, 20)))
                .doesNotContain(companyInfo1)
                .doesNotContain(companyInfo2);
    }

    @Test
    public void findAll() {

        Position position = entityManager.persist(new Position("test"));

        CompanyInfo companyInfo1 = entityManager.persist(new CompanyInfo("Test1",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));
        CompanyInfo companyInfo2 = entityManager.persist(new CompanyInfo("Test2",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));
        CompanyInfo companyInfo3 = entityManager.persist(new CompanyInfo("Test3",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));


        Iterable<CompanyInfo> companyInfos = companyInfoRepository.findAll();

        assertThat(companyInfos).contains(companyInfo1, companyInfo2, companyInfo3);
    }

    @Test
    public void findOne() {

        Position position = entityManager.persist(new Position("test"));

        CompanyInfo companyInfo1 = entityManager.persist(new CompanyInfo("Test1",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));
        CompanyInfo companyInfo2 = entityManager.persist(new CompanyInfo("Test2",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));

        CompanyInfo companyInfo = companyInfoRepository.findOne(companyInfo2.getId());

        assertThat(companyInfo).isEqualTo(companyInfo2);
    }

    @Test
    public void findByName() {

        Position position = entityManager.persist(new Position("test"));

        CompanyInfo companyInfo1 = entityManager.persist(new CompanyInfo("Test1",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));
        CompanyInfo companyInfo2 = entityManager.persist(new CompanyInfo("Test2",
                LocalDate.of(1970, Month.JANUARY, 1),
                LocalDate.of(1970, Month.JANUARY, 1),
                position));

        Iterable<CompanyInfo> companyInfos = companyInfoRepository.findByNameContainingIgnoreCase("ST1", new PageRequest(0, 20));

        assertThat(companyInfos).contains(companyInfo1);
        assertThat(companyInfos).doesNotContain(companyInfo2);
    }

}