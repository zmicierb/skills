package com.barysevich.project.repository;

import com.barysevich.project.model.SkillSum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface SkillSumRepository extends PagingAndSortingRepository<SkillSum, Long> {

    Iterable<SkillSum> findByPersonId(@Param("id") Long id);

    @Transactional
    @Modifying
    void deleteByPersonId(@Param("id") Long personId);

    Iterable<SkillSum> findBySkillNameContainingIgnoreCase(@Param("name") String name);
}
