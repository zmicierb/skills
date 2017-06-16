package com.barysevich.project.repository;

import com.barysevich.project.model.EnvironmentSkill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by BarysevichD on 2017-03-15.
 */
@RepositoryRestResource(exported = false)
public interface EnvironmentSkillRepository extends PagingAndSortingRepository<EnvironmentSkill, Long> {
}
