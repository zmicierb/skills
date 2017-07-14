package com.barysevich.project.repository;

import com.barysevich.project.model.SkillSum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface SkillSumRepository extends PagingAndSortingRepository<SkillSum, Long> {

    Iterable<SkillSum> findByPersonId(@Param("personId") Long personId);

    @Query("SELECT s FROM SkillSum s JOIN FETCH s.row JOIN FETCH s.skill WHERE s.personId = :personId")
    Iterable<SkillSum> findByPersonIdEager(@Param("personId") Long personId);

    @Transactional
    @Modifying
    void deleteByPersonId(@Param("id") Long personId);

    Iterable<SkillSum> findBySkillNameContainingIgnoreCase(@Param("name") String name);

    Integer countByPersonId(@Param("personId") Long personId);

    @Query("SELECT s FROM SkillSum s WHERE s.personId = :personId AND s.totalAmount <> :totalAmount OR s.totalAmount is null")
    Iterable<SkillSum> checkTotalAmountByPersonId(@Param("personId") Long personId, @Param("totalAmount") Integer totalAmount);
}
